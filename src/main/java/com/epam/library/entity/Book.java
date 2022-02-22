package com.epam.library.entity;

public class Book extends Entity {
    private String title;
    private Long authorId;
    private int stock;
    private long genreId;

    public Book(Long id, String title, Long authorId, int stock, long genreId) {
        super(id);
        this.title = title;
        this.authorId = authorId;
        this.stock = stock;
        this.genreId = genreId;
    }

    public Long getAuthorId() {
        return authorId;
    }

    public void setAuthorId(Long authorId) {
        this.authorId = authorId;
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
