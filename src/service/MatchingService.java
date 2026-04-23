package service;

import domain.Offer;
import domain.Request;
import domain.Skill;
import domain.Student;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.regex.MatchResult;
import java.util.stream.Collectors;

import status.Maps;

public final class MatchingService {
    public List<MatchResult> findOneWayMatches(String studentId, SkillSwapState state) {

        Optional<Student> studentOpt = findStudentById(studentId, state);
        if (studentOpt.isEmpty()) return List.of();

        Student student = studentOpt.get();

        List<Request> myRequests = state.getRequests().stream()
                .filter(r -> r.getStudent().getId().equals(studentId))
                .collect(Collectors.toList());

        List<MatchResult> results = new ArrayList<>();

        for (Request myRequest : myRequests) {
            for (Offer offer : state.getOffers()) {
,
                // skip self
                if (offer.getStudent().getID().equals(studentId)) continue;

                // solo offerte attive
                if (!offer.isActive()) continue;

                // match skill
                if (!offer.getSkill().getName().equalsIgnoreCase(myRequest.getSkill().getName()))
                    continue;

                int score = calculateScore(student, offer.getStudent(), myRequest.getSkill(), offer.getSkill());
                String reason = buildReason(student, offer.getStudent(), myRequest.getSkill(), offer.getSkill());

                results.add(new MatchResult(
                        offer.getStudent(),
                        offer,
                        myRequest,
                        score,
                        reason
                ));
            }
        }

        return results.stream()
                .sorted(Comparator.comparingInt(MatchResult::getScore).reversed())
                .collect(Collectors.toList());
    }

    private int calculateScore(Student requester, Student provider, Skill requestedSkill, Skill offeredSkill) {
        int score = 0;

        // +3 skill match (già garantito)
        score += 3;

        // +2 livello sufficiente
        if (offeredSkill.getLevel() >= requestedSkill.getLevel()) {
            score += 2;
        }

        // +1 stessa classe
        if (requester.getClassGroup() != null &&
            requester.getClassGroup().equals(provider.getClassGroup())) {
            score += 1;
        }

        return score;
    }

    private String buildReason(Student requester, Student provider, Skill requestedSkill, Skill offeredSkill) {
        List<String> reasons = new ArrayList<>();

        reasons.add("Skill match");

        if (offeredSkill.getLevel() >= requestedSkill.getLevel()) {
            reasons.add("Livello adeguato");
        }

        if (requester.getClassGroup() != null &&
            requester.getClassGroup().equals(provider.getClassGroup())) {
            reasons.add("Stessa classe");
        }

        return String.join(", ", reasons);
    }

}