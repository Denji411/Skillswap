package app;

import domain.Exchange;
import domain.Offer;
import domain.Request;
import domain.Skill;
import domain.Student;
import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.DateTimeException;
import java.time.LocalDateTime;
import java.util.*;
import resources.Category;
import resources.Level;
import resources.NextID;
import resources.ParserUtils;
import resources.Status;
import service.Review;
import status.Maps;

public class Main {
    private static final Scanner scanner = new Scanner(System.in);

    private static int nextStudentID = 1;
    private static int nextOfferID = 1;
    private static int nextRequestID = 1;

    private static Map<String, Runnable> comandi = new HashMap<>();

    public static void main(String[] args) {

        Path studentsPath = Path.of("data", "students.csv");
        Path skillsPath = Path.of("data", "skills.csv");
        Path offersPath = Path.of("data", "offers.csv");
        Path requestsPath = Path.of("data", "requests.csv");
        Path exchangesPath = Path.of("data", "exchanges.csv");
        Path reviewsPath = Path.of("data", "reviews.csv");

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
                try {
                    Maps.addStudent(riga);
                } catch (NumberFormatException e) {
                    e.printStackTrace();
                } 
            }

            while((riga = brSkills.readLine()) != null) {
                try {
                    Maps.addSkill(riga);
                } catch (IllegalArgumentException e) {
                    e.printStackTrace();
                }
            }

            while((riga = brOffers.readLine()) != null) {
                try {
                    Maps.addOffer(riga);
                } catch (IllegalArgumentException e) {
                    e.printStackTrace();
                }
            }

            while((riga = brRequests.readLine()) != null) {
                try {
                    Maps.addRequest(riga);
                } catch (IllegalArgumentException e) {
                    e.printStackTrace();
                }
            }

            while((riga = brExchanges.readLine()) != null) {
                try {
                    Maps.addExchanges(riga);
                } catch(IllegalArgumentException | DateTimeException e) {
                    e.printStackTrace();
                }
            }

            while((riga = brReviews.readLine()) != null) {
                String[] dati = riga.split(";");
                try {
                    if (dati.length == 7) {
                        Exchange ex = exchanges.get(dati[1]);
                        Student s1 = students.get(dati[2]);
                        Student s2 = students.get(dati[3]);

                        if(ex == null || s1 == null || s2 == null) {
                            System.out.println("Exchange o student non trovato: " + riga);
                            continue;
                        }

                        double stars = ParserUtils.parseStars(dati[4]);
                        LocalDateTime createdAt = ParserUtils.parseLocalDate(dati[6]);
                        
                        Review rv = new Review(dati[0], ex, s1, s2, stars, dati[5], createdAt); 
                        reviews.put(rv.getID(), rv);
                    } else {
                        System.out.println("Dati insufficienti: " + riga);
                    }
                } catch(NumberFormatException | DateTimeException e) {
                    e.printStackTrace();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        nextStudentID = NextID.initNextStudentID(students);
        nextOfferID = NextID.initNextOfferID(offers);
        nextRequestID = NextID.initNextRequestID(requests);

        comandi.put("cs", Main::createStudent);
        comandi.put("addo", Main::addOffer);
        comandi.put("addr", Main::addRequest);
        comandi.put("print", Main::printList);
        
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

        String ID = "S" + nextStudentID;
        String name = "";
        String studentClass;
        String email;
            
        System.out.println("Inserisci il nome studente: ");
        System.out.println();
        System.out.print("> ");
        String input = scanner.nextLine().trim();
        String[] parts = input.split(" ");
        for (int i = 0; i < parts.length; i++) {
            String p = parts[i];
            name += p.substring(0,1).toUpperCase() + p.substring(1).toLowerCase() + " ";
        }

        System.out.println("Inserisci la classe: ");
        System.out.println();
        System.out.print("> ");
        studentClass = scanner.nextLine().trim().toUpperCase();

        System.out.println("Inserisci la email: ");
        System.out.println();
        System.out.print("> ");
        email = scanner.nextLine().trim();

        Student s = new Student(ID, name, studentClass, email, 0, 0);
        students.put(s.getID(), s);
    }

    private static void addOffer() {
        System.out.println("Aggiungo Offerta");

        System.out.print("Inserisci ID Studente: ");
        String studentId = scanner.nextLine().trim();
        Student s = students.get(studentId);

        if (s == null) {
            System.out.println("Studente non trovato");
            return;
        }

        System.out.print("Inserisci ID Skill: ");
        String skillId = scanner.nextLine().trim();
        Skill sk = skills.get(skillId);

        if (sk == null) {
            System.out.println("Skill non trovata");
            return;
        }

        String ID = "O" + nextOfferID;

        System.out.print("Inserisci una descrizione: ");
        String desc = scanner.toString();

        Offer o = new Offer(ID, desc, Level.ADVANCED, true, s, sk);
        offers.put(o.getID(), o);

        System.out.println("Offerta creata con ID: " + ID);
    }

    private static void addRequest() {
        System.out.println("Aggiungo Richiesta");

        System.out.print("Inserisci ID Studente: ");
        String studentId = scanner.nextLine().trim();
        Student s = students.get(studentId);

        if (s == null) {
            System.out.println("Studente non trovato");
            return;
        }

        System.out.print("Inserisci ID Skill: ");
        String skillId = scanner.nextLine().trim();
        Skill sk = skills.get(skillId);

        if (sk == null) {
            System.out.println("Skill non trovata");
            return;
        }

        System.out.print("Inserisci livello (BEGINNER, INTERMEDIATE, ADVANCED): ");
        String levelInput = scanner.nextLine().trim();

        Level level;
        try {
            level = ParserUtils.parseLevel(levelInput);
        } catch (IllegalArgumentException e) {
            System.out.println("Livello non valido");
            return;
        }

        System.out.print("Inserisci Descrizione: ");
        String desc = scanner.nextLine().trim();

        String ID = "R" + nextRequestID;

        Request r = new Request(ID, s, sk, level, desc);
        requests.put(r.getID(), r);

        System.out.println("Richiesta creata con ID: " + ID);
    }

    private static void printList() {
        System.out.println("Lista Offers:");
        for (Offer o : offers.values()) {
            System.out.println(o);
        }

        System.out.println("Lista Requests:");
        for (Request r : requests.values()) {
                System.out.println(r);
        }
    }
}