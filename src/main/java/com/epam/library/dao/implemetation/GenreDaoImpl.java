package com.epam.library.dao.implemetation;

import com.epam.library.dao.AbstractDao;
import com.epam.library.entity.Genre;

import java.sql.Connection;
import java.util.List;
import java.util.Optional;

public class GenreDao extends AbstractDao<Genre> {

    public GenreDao(Connection connection) {
        super(connection);
    }

    @Override
    protected String getTableName() {
        return null;
    }

    @Override
    public Optional<Genre> getById(Long id) {
        return Optional.empty();
    }

    @Override
    public void save(Genre item) {

    }

    @Override
    public void removeById(Long id) {

    }
}
