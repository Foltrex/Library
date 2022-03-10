package com.epam.library.entity;

import java.util.Objects;

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

    public static Author createAuthorWithOnlyIDField(Long id) {
        return new Author(id, null, null);
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

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Author)) {
            return false;
        }
        if (!super.equals(o)) {
            return false;
        }
        Author author = (Author) o;
        return Objects.equals(name, author.name) && Objects.equals(surname, author.surname);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), name, surname);
    }

    @Override
    public String toString() {
        return "Author{" +
                "name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                '}';
    }
}
