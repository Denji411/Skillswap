package domain;

public class Request {
    private Student student;
    private Skill skill;
    
    public Request(Student student, Skill skill) {
        this.student = student;
        this.skill = skill;
    }
}