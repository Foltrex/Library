package com.epam.library.entity;

public class Genre extends Entity {

    public static final String TABLE = "genres";

    public static final String NAME = "name";

    private String name;

    public Genre(Long id, String name) {
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
