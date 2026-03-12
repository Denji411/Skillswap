package app;

import domain.Exchange;
import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

import domain.Offer;
import domain.Request;
import domain.Skill;
import domain.Student;

import java.io.File;
import java.time.DateTimeException;
import java.time.LocalDateTime;
import service.Review;

public class Main {
    
    private static Map<String, Runnable> comandi = new HashMap<>();
    public static void main(String[] args) {
        Map<String, Student> students = new HashMap<>();
        Map<String, Skill> skills = new HashMap<>();
        Map<String, Offer> offers = new HashMap<>();
        Map<String, Request> requests = new HashMap<>();
        Map<String, Exchange> exchanges = new HashMap<>();
        Map<String, Review> reviews = new HashMap<>();

        Path studentsPath = Path.of("data", "students.csv");
        Path skillsPath = Path.of("data", "skills.scv");
        Path offersPath = Path.of("data", "offers.csv");
        Path requestsPath = Path.of("dati", "requests.csv");
        Path exchangesPath = Path.of("dati", "exchanges.csv");
        Path reviewsPath = Path.of("dati", "reviews.csv");

        try (
            BufferedReader brStudents = Files.newBufferedReader(studentsPath);
            BufferedReader brSkills = Files.newBufferedReader(skillsPath);
            BufferedReader brOffers = Files.newBufferedReader(offersPath);
            BufferedReader brRequests = Files.newBufferedReader(requestsPath);
            BufferedReader brExchanges = Files.newBufferedReader(exchangesPath);
            BufferedReader brReviews = Files.newBufferedReader(reviewsPath)
        ) {
            String riga;
            while((riga = brStudents.readLine()) != null) {
                String[] dati = riga.split(";");
                if (dati.length == 6) {
                    try {
                        double avgRating = parseAvgRating(dati[4]);
                        int ratingCount = parseRatingcount(dati[5]);
                        
                        Student s = new Student(dati[0], dati[1], dati[2], dati[3], avgRating, ratingCount);
                        students.put(s.getID(), s);
                    } catch (NumberFormatException e) {
                        e.getMessage();
                    } 
                } else {
                    System.out.println("Dati insufficienti");
                }
            }

            while((riga = brSkills.readLine()) != null) {
                String[] dati = riga.split(";");
                if (dati.length == 3) {
                    try {
                        Category category = parseCategory(dati[2]);
                        
                        Skill sk = new Skill(dati[0], dati[1], category);
                        skills.put(sk.getID(), sk);
                    } catch (IllegalArgumentException e) {
                        e.getMessage();
                    } 
                } else {
                    System.out.println("Dati insufficienti");
                }
            }

            while((riga = brOffers.readLine()) != null) {
                String[] dati = riga.split(";");
                if (dati.length == 2) {
                    Student s = students.get(dati[0]);
                    Skill sk = skills.get(dati[1]);
                    
                    Offer o = new Offer(s, sk);
                    offers.put(o.getID(), o);
                } else {
                    System.out.println("Dati insufficienti");
                }
            }

            while((riga = brRequests.readLine()) != null) {
                String[] dati = riga.split(";");
                if (dati.length == 2) {
                    Student s = students.get(dati[0]);
                    Skill sk = skills.get(dati[1]);
                    
                    Request r = new Request(s, sk);
                    requests.put(r.getID(), r);
                } else {
                    System.out.println("Dati insufficienti");
                }
            }

            while((riga = brExchanges.readLine()) != null) {
                String[] dati = riga.split(";");
                try {
                    if (dati.length == 6) {
                        Offer o = offers.get(dati[0]);
                        Request r = requests.get(dati[1]);
                        Status status = parseStatus(dati[3]);
                        LocalDateTime createdAt = parseLocalDate(dati[4]);
                        LocalDateTime closedAt = parseLocalDate(dati[5]);

                        Exchange ex = new Exchange(dati[0], o, r, status, createdAt, closedAt); 
                    } else {
                        System.out.println("Dati insufficienti");
                    }
                } catch(IllegalArgumentException e) {
                    e.getMessage();
                } catch(DateTimeException e) {
                    e.getMessage();
                }
            }

            while((riga = brReviews.readLine()) != null) {
                String[] dati = riga.split(";");
                if (dati.length == 3) {
                    Exchange ex = exchanges.get(dati[0]);
                    Student s1 = students.get(dati[1]);
                    Student s2 = students.get(dati[2]);
                    
                    Review rv = new Review(ex, s1, s2); 
                } else {
                    System.out.println("Dati insufficienti");
                }
            }

        } catch (IOException e) {
            e.getMessage();
        }

        comandi.put("cs", Main::createStudent);
        comandi.put("addo", Main::addOffer);
        comandi.put("addr", Main::addRequest);
        comandi.put("print", Main::printList);
        
        final Scanner scanner = new Scanner(System.in);  
        
        while(true) {
            System.out.print("> ");
            String input = scanner.nextLine().trim();
            String[] parts = input.split(" ");
            String cmd = parts[0];

            if (comandi.containsKey(cmd)) {
                comandi.get(cmd).run();
            } else {
                System.out.println("Comando sconosciuto");
            }
        }
    }

    private static void createStudent() {
        System.out.println("Creo Studente");
    }

    private static void addOffer() {
        System.out.println("Aggiungo Offerta"); 
    }

    private static void addRequest() {
        System.out.println("Aggiungo Richiesta");
    }

    private static void printList() {
        System.out.println("Stampo Liste");
    }
}