package com.epam.library.entity;

import lombok.Builder;
import lombok.Value;

import java.io.Serializable;
import java.util.Objects;

@Value
@Builder
public class Author implements Identifable, Serializable {
    public static final String TABLE = "authors";

    public static final String ID = "id";
    public static final String NAME = "name";
    public static final String SURNAME = "surname";

    Long id;
    String name;
    String surname;

    public Author(Long id, String name, String surname) {
        this.id = id;
        this.name = name;
        this.surname = surname;
    }

    public static Author createAuthorWithOnlyIDField(Long id) {
        return new Author(id, null, null);
    }
}
