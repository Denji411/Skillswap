package domain;

public class Request {
    private String ID;
    private Student student;
    private Skill skill;
    
    public Request(Student student, Skill skill) {
        this.student = student;
        this.skill = skill;
    }

    public String getID() {
        return ID;
    }
}