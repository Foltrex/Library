package com.epam.library.entity;

import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Genre)) {
            return false;
        }
        if (!super.equals(o)) {
            return false;
        }
        Genre genre = (Genre) o;
        return Objects.equals(name, genre.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), name);
    }

    @Override
    public String toString() {
        return "Genre{" +
                "name='" + name + '\'' +
                '}';
    }
}
