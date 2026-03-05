package domain;

public class Offer {
    private String ID;
    private String note;
    private boolean active;
    private Student student;
    private Skill skill;

    public Offer(Student student, Skill skill) {
        this.student = student;
        this.skill = skill;
    }
}