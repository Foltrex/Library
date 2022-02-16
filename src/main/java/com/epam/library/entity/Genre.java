package com.epam.library.entity;

public class Genre extends Entity {
    private String name;

    public static final String TABLE = "genres";

    public Genre(long id, String name) {
        super(id);
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
