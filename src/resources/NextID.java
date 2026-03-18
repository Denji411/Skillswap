package resources;

import java.util.Map;

import domain.Offer;
import domain.Request;
import domain.Student;

public final class NextID {
    public static int initNextStudentID(Map<String, Student> students) {
        int max = 0;

        for (String id : students.keySet()) {
            if (id.startsWith("S")) {
                try {
                    int num = Integer.parseInt(id.substring(1));
                    if (num > max) {
                        max = num;
                    }
                } catch (NumberFormatException e) {
                    // Ignora ID malformati
                }
            }
        }

        return max + 1;
    }
    
    public static int initNextOfferID(Map<String, Offer> offers) {
        int max = 0;

        for (String ID : offers.keySet()) {
            if (ID.startsWith("S")) {
                try {
                    int num = Integer.parseInt(ID.substring(1));
                    if (num > max) {
                        max = num;
                    }
                } catch (NumberFormatException e) {
                    // Ignora ID malformati
                }
            }
        }

        return max + 1;
    }

    public static int initNextRequestID(Map<String, Request> requests) {
        int max = 0;

        for (String ID : requests.keySet()) {
            if (ID.startsWith("S")) {
                try {
                    int num = Integer.parseInt(ID.substring(1));
                    if (num > max) {
                        max = num;
                    }
                } catch (NumberFormatException e) {
                    // Ignora ID malformati
                }
            }
        }

        return max + 1;
    }
}
