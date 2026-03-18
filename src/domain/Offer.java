package domain;

public class Offer {
    private String ID;
    private String note;
    private boolean active;
    private Student student;
    private Skill skill;

    public Offer(String ID, String note, boolean active, Student student, Skill skill) {
        this.ID = ID;
        this.note = note;
        this.active = active;
        this.student = student;
        this.skill = skill;
    }

    public String getID() {
        return ID;
    }
}