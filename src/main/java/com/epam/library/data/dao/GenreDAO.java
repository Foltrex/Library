package com.epam.library.data.dao;

import com.epam.library.models.Genre;

import java.sql.Connection;
import java.util.List;

public class GenreDAO extends AbstractDAO<Genre> {

    public GenreDAO(Connection connection) {
        super(connection);
    }

    @Override
    public List<Genre> findAll() {
        return null;
    }

    @Override
    public Genre findEntityById(int id) {
        return null;
    }

    @Override
    public boolean delete(int id) {
        return false;
    }

    @Override
    public boolean delete(Genre entity) {
        return false;
    }

    @Override
    public boolean create(Genre entity) {
        return false;
    }

    @Override
    public Genre update(Genre entity) {
        return null;
    }
}
