package com.epam.library.entity;

public class Book {
    private int id;
    private String title;
    private Author author;
    private int stock;
    private Genre genre;

    public Book(String title, Author author, int stock, Genre genre) {
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

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
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
