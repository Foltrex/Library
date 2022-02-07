package com.epam.library.data.dao;

import com.epam.library.models.Book;

import java.sql.Connection;
import java.util.List;

public class BookDAO extends AbstractDAO<Book> {

    public BookDAO(Connection connection) {
        super(connection);
    }

    @Override
    public List<Book> findAll() {
        return null;
    }

    @Override
    public Book findEntityById(int id) {
        return null;
    }

    @Override
    public boolean delete(int id) {
        return false;
    }

    @Override
    public boolean delete(Book entity) {
        return false;
    }

    @Override
    public boolean create(Book entity) {
        return false;
    }

    @Override
    public Book update(Book entity) {
        return null;
    }
}
