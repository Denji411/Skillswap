package status;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import domain.Exchange;
import domain.Offer;
import domain.Request;
import domain.Skill;
import domain.Student;
import resources.Category;
import resources.Level;
import resources.ParserUtils;
import resources.Status;
import service.Review;

public final class Maps {
    private static Map<String, Student> students = new HashMap<>();
    private static Map<String, Skill> skills = new HashMap<>();
    private static Map<String, Offer> offers = new HashMap<>();
    private static Map<String, Request> requests = new HashMap<>();
    private static Map<String, Exchange> exchanges = new HashMap<>();
    private static Map<String, Review> reviews = new HashMap<>();

    public static void addStudent(String st) throws NumberFormatException {
        String[] dati = st.split(";");
        if (dati.length == 6) {
            double avgRating = ParserUtils.parseAvgRating(dati[4]);
            int ratingCount = ParserUtils.parseRatingcount(dati[5]);
   
            Student s = new Student(dati[0], dati[1], dati[2], dati[3], avgRating, ratingCount);
            students.put(s.getID(), s);
        } else {
            System.out.println("Dati insufficienti: " + st);
        }
    }

    public static void addSkill(String st) throws IllegalArgumentException {
        String[] dati = st.split(";");
        if (dati.length == 3) {
            Category category = ParserUtils.parseCategory(dati[2]);
        
            Skill sk = new Skill(dati[0], dati[1], category);
            skills.put(sk.getID(), sk);
        } else {
            System.out.println("Dati insufficienti: " + st);
        }
    }

    public static void addOffer(String st) throws IllegalArgumentException {
        String[] dati = st.split(";");
        if (dati.length == 6) {
            Level level = ParserUtils.parseLevel(dati[2]);
            boolean active = ParserUtils.parseActive(dati[3]);
            Student s = students.get(dati[4]);
            Skill sk = skills.get(dati[5]);

            if(s == null || sk == null) {
                System.out.println("Student o skill non trovato: " + st);
                continue;
            }
            
            Offer o = new Offer(dati[0], dati[1], level, active, s, sk);
            offers.put(o.getID(), o);
        } else {
            System.out.println("Dati insufficienti: " + st);
        }
    }

    public static void addRequest(String st) throws IllegalArgumentException {
        String[] dati = riga.split(";");
        if (dati.length == 5) {
            Student s = students.get(dati[1]);
            Skill sk = skills.get(dati[2]);
            Level level = ParserUtils.parseLevel(dati[3]);

            if(s == null || sk == null) {
                System.out.println("Student o skill non trovato: " + st);
                continue;
            }
            
            Request r = new Request(dati[0], s, sk, level, dati[4]);
            requests.put(r.getID(), r);
        } else {
            System.out.println("Dati insufficienti: " + st);
        }
    }

    public static void addExchanges(String st) throws IllegalArgumentException | DateTimeException {
        String[] dati = riga.split(";");
        if (dati.length == 6) {
            Offer o = offers.get(dati[1]);
            Request r = requests.get(dati[2]);

            if(o == null || r == null) {
                System.out.println("Offer o request non trovato: " + st);
                continue;
            }

            Status status = ParserUtils.parseStatus(dati[3]);
            LocalDateTime createdAt = ParserUtils.parseLocalDate(dati[4]);
            LocalDateTime closedAt = ParserUtils.parseLocalDate(dati[5]);

            Exchange ex = new Exchange(dati[0], o, r, status, createdAt, closedAt); 
            exchanges.put(ex.getID(), ex);
        } else {
            System.out.println("Dati insufficienti: " + st);
        }
    }
}