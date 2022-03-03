package com.epam.library.entity;

public class Genre extends Entity {

    public static final String TABLE = "genres";

    public static final String NAME = "name";


    private String name;

    public Genre(Long id, String name) {
        super(id);
        this.name = name;
    }

    public static Genre createGenreWithOnlyIDField(Long id) {
        return new Genre(id, null);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
