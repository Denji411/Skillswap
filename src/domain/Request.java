package domain;

import resources.Level;

public class Request {
    private String ID;
    private Student student;
    private Skill skill;
    private Level minLevel;
    private String note;

    private static final Pattern ID_PATTERN =
           Pattern.compile("^[R]\\d+$");
    
    public Request(String ID, Student student, Skill skill, Level minLevel, String note) {
        if (!isValidID(ID)) throw new IllegalArgumentException("Id non valido");

        this.ID = ID;
        this.student = student;
        this.skill = skill;
        this.minLevel = minLevel;
        this.note = note;
    }

    private boolean isValidID(String ID) {
        return ID != null && ID_PATTERN.matcher(ID).matches();
    }

    public String getID() {
        return ID;
    }
}