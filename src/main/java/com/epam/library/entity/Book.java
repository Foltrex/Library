package com.epam.library.entity;

import lombok.Builder;
import lombok.Value;

import java.io.Serializable;
import java.util.Objects;

@Value
@Builder
public class Book implements Identifable, Serializable {

    public static final String TABLE = "books";

    public static final String ID = "id";
    public static final String TITLE = "title";
    public static final String AUTHOR_ID = "author_id";
    public static final String AUTHOR_NAME = "author_name";
    public static final String AUTHOR_SURNAME = "author_surname";
    public static final String STOCK = "stock";
    public static final String GENRE_ID = "genre_id";
    public static final String GENRE_NAME = "genre_name";

    Long id;
    String title;
    Author author;
    int stock;
    Genre genre;

    public Book(Long id, String title, Author author, int stock, Genre genre) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.stock = stock;
        this.genre = genre;
    }

    public static Book createBookWithOnlyId(Long id) {
        return new Book(id, null, null,0, null);
    }

    public static Book createBookWithIDAndTitle(Long id, String title) {
        return new Book(id, title, null, 0, null);
    }
}
