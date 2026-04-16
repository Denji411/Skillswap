package domain;

import java.util.regex.Pattern;
import resources.Level;

public class Offer {
    private String ID;
    private String note;
    private Level level;
    private boolean active;
    private Student student;
    private Skill skill;

    private static final Pattern ID_PATTERN =
           Pattern.compile("^[O]\\d+$");

    public Offer(String ID, String note, Level level, boolean active, Student student, Skill skill) {
        if (!isValidID(ID)) throw new IllegalArgumentException("Id non valido");

        this.ID = ID;
        this.note = note;
        this.level = level;
        this.active = active;
        this.student = student;
        this.skill = skill;
    }

    private boolean isValidID(String ID) {
        return ID != null && ID_PATTERN.matcher(ID).matches();
    }

    public String getID() {
        return ID;
    }
}