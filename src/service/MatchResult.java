package it.skillswap.service;

import it.skillswap.domain.Offer;
import it.skillswap.domain.Request;
import it.skillswap.domain.Student;

public class MatchResult {

    private final Student matchedStudent;
    private final Offer offer;
    private final Request request;
    private final int score;
    private final String reason;

    public MatchResult(Student matchedStudent, Offer offer, Request request, int score, String reason) {
        this.matchedStudent = matchedStudent;
        this.offer = offer;
        this.request = request;
        this.score = score;
        this.reason = reason;
    }

    public Student getMatchedStudent() {
        return matchedStudent;
    }

    public Offer getOffer() {
        return offer;
    }

    public Request getRequest() {
        return request;
    }

    public int getScore() {
        return score;
    }

    public String getReason() {
        return reason;
    }

    @Override
    public String toString() {
        return "MatchResult{" +
                "matchedStudent=" + matchedStudent.getId() +
                ", skillOffered=" + offer.getSkill().getName() +
                ", skillRequested=" + request.getSkill().getName() +
                ", score=" + score +
                ", reason='" + reason + '\'' +
                '}';
    }
}