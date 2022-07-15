package com.epam.library.entity;

import lombok.Builder;
import lombok.Value;

import java.io.Serializable;
import java.util.Objects;

@Value
@Builder
public class Genre implements Identifable, Serializable {

    public static final String TABLE = "genres";

    public static final String ID = "id";
    public static final String NAME = "name";

    Long id;
    String name;

    public Genre(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public static Genre createGenreWithOnlyIDField(Long id) {
        return new Genre(id, null);
    }
}
