package com.epam.library.entity;

public class Book extends Entity {

    public static final String TABLE = "books";

    public static final String TITLE = "title";
    public static final String AUTHOR_ID = "author_id";
    public static final String AUTHOR_NAME = "author_name";
    public static final String AUTHOR_SURNAME = "author_surname";
    public static final String STOCK = "stock";
    public static final String GENRE_ID = "genre_id";
    public static final String GENRE_NAME = "genre_name";

    private String title;
    private Author author;
    private int stock;
    private Genre genre;

    public Book(Long id, String title, Author author, int stock, Genre genre) {
        super(id);
        this.title = title;
        this.author = author;
        this.stock = stock;
        this.genre = genre;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public Genre getGenre() {
        return genre;
    }

    public void setGenre(Genre genre) {
        this.genre = genre;
    }
}
