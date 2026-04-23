package domain;

import java.util.regex.Pattern;
import resources.Category;

public class Skill {
    private String ID;
    private String name;
    private Category category;

    private static final Pattern ID_PATTERN =
           Pattern.compile("^[K]\\d+$");

    public Skill(String ID, String name, Category category) {
        if (!isValidID(ID)) throw new IllegalArgumentException("Id non valido");
        if (name == null || name.isBlank()) throw new IllegalArgumentException("Nome non valido");

        this.ID = ID;
        this.name = name;
        this.category = category;
    }

    private boolean isValidID(String ID) {
        return ID != null && ID_PATTERN.matcher(ID).matches();
    }

    public String getID() {
        return ID;
    }

    public String getName() {
        return name;
    }
}