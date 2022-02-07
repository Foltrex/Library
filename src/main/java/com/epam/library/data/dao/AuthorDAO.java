package com.epam.library.data.dao;

import com.epam.library.models.Author;

import java.sql.Connection;
import java.util.List;

public class AuthorDAO extends AbstractDAO<Author> {

    public AuthorDAO(Connection connection) {
        super(connection);
    }

    @Override
    public List<Author> findAll() {
        return null;
    }

    @Override
    public Author findEntityById(int id) {
        return null;
    }

    @Override
    public boolean delete(int id) {
        return false;
    }

    @Override
    public boolean delete(Author entity) {
        return false;
    }

    @Override
    public boolean create(Author entity) {
        return false;
    }

    @Override
    public Author update(Author entity) {
        return null;
    }
}
