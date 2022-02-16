package com.epam.library.entity;

public class Author extends Entity {
    private String name;
    private String surname;
    //List<Book> books;

    public static final String TABLE = "authors";

    public Author(long id, String name, String surname) {
        super(id);
        this.name = name;
        this.surname = surname;
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
