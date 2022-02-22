package com.epam.library.entity;

public class Genre extends Entity {
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
