package com.epam.library.models;

import java.util.List;

public class Book extends Entity {
    private String title;
    private List<Author> authors;
    private int stock;
    private Genre genre;

    public Book(int id, String title, List<Author> authors, int stock, Genre genre) {
        super(id);
        this.title = title;
        this.authors = authors;
        this.stock = stock;
        this.genre = genre;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<Author> getAuthors() {
        return authors;
    }

    public void setAuthors(List<Author> authors) {
        this.authors = authors;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public Genre getGenre() {
        return genre;
    }

    public void setGenre(Genre genre) {
        this.genre = genre;
    }
}
