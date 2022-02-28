package com.epam.library.entity;

public class Author extends Entity {
    public static final String TABLE = "authors";

    public static final String NAME = "name";
    public static final String SURNAME = "surname";

    private String name;
    private String surname;

    public Author(Long id, String name, String surname) {
        super(id);
        this.name = name;
        this.surname = surname;
    }


    public Author(Long id) {
        super(id);
        this.name = "";
        this.surname = "";
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }
}
