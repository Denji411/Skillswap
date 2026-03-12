package domain;

public class Student {
    private String ID;
    private String name;
    private String studentClass;
    private String email;
    private double avgRating;
    private int ratingCount; 
    
    public Student(String ID, String name, String studentClass, String email, double avgRating, int ratingCount) {
        this.ID = ID;
        this.name = name;
        this.studentClass = studentClass;
        this.email = email;
        this.avgRating = avgRating;
        this.ratingCount = ratingCount;
    }

    public double parseAvgRating(String s) throws NumberFormatException {
        return Double.parseDouble(s);
    }

    public int parseRatingcount(String s) throws NumberFormatException {
        return Integer.parseInt(s);
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
