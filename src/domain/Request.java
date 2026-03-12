package domain;

import resources.Level;

public class Request {
    private String ID;
    private Student student;
    private Skill skill;
    private Level minLevel;
    private String note;
    
    public Request(String ID, Student student, Skill skill, Level minLevel, String note) {
        this.ID = ID;
        this.student = student;
        this.skill = skill;
        this.minLevel = minLevel;
        this.note = note;
    }

    public Level parseLevel(String s) throws IllegalArgumentException {
        return Level.valueOf(s);
    }

    public String getID() {
        return ID;
    }
}