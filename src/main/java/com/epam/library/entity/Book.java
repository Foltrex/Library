package com.epam.library.entity;

public class Book extends Entity {
    private String title;
    //private List<Author> authors;
    private int stock;
    private long genreId;

    public static final String TABLE = "books";

    public Book(long id, String title, int stock, long genreId) {
        super(id);
        this.title = title;
        this.stock = stock;
        this.genreId = genreId;
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

    public long getGenreId() {
        return genreId;
    }

    public void setGenreId(long genreId) {
        this.genreId = genreId;
    }
}
