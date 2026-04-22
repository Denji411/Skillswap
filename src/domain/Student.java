package domain;

import java.util.regex.Pattern;

public class Student {
    private String ID;
    private String name;
    private String studentClass;
    private String email;
    private double avgRating;
    private int ratingCount; 

    private static final Pattern ID_PATTERN =
           Pattern.compile("^[S]\\d+$");

    private static final Pattern EMAIL_PATTERN =
           Pattern.compile("^[\\w.-]+@[\\w.-]+\\.[a-zA-Z]{2,}$");

    private static final Pattern CLASS_PATTERN =
           Pattern.compile("^[1-5][A-Z]$"); 
    
    public Student(String ID, String name, String studentClass, String email, double avgRating, int ratingCount) {
        if (!isValidID(ID)) throw new IllegalArgumentException("Id non valido");
        if (name == null || name.isBlank()) throw new IllegalArgumentException("Nome non valido");
        if (!isValidClass(studentClass)) throw new IllegalArgumentException("Classe non valida");
        if (!isValidEmail(email)) throw new IllegalArgumentException("Email non valida");
        
        this.ID = ID;
        this.name = name;
        this.studentClass = studentClass;
        this.email = email;
        this.avgRating = avgRating;
        this.ratingCount = ratingCount;
    }

    private boolean isValidID(String ID) {
        return ID != null && ID_PATTERN.matcher(ID).matches();
    }

    private boolean isValidEmail(String email) {
        return email != null && EMAIL_PATTERN.matcher(email).matches();
    }

    private boolean isValidClass(String studentClass) {
        return studentClass != null && CLASS_PATTERN.matcher(studentClass).matches();
    }

    public String getID() {
        return ID;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Student altro) {
            return this.ID.equals(altro.ID);
        }
        return false;
    }

    @Override
    public int hashCode() {
        int result = 17;
        return result *= 31 + ID.hashCode();
    }

    @Override
    public String toString() {
        return ID + name;
    }
}
