package domain;

import resources.Category;

public class Skill {
    private String ID;
    private String name;
    private Category category;

    public Skill(String ID, String name, Category category) {
        this.ID = ID;
        this.name = name;
        this.category = category;
    }

    public String getID() {
        return ID;
    }
}