package com.epam.library.dao.implemetation;

import com.epam.library.dao.AbstractDao;
import com.epam.library.entity.Author;

import java.sql.Connection;
import java.util.List;
import java.util.Optional;

public class AuthorDao extends AbstractDao<Author> {

    public AuthorDao(Connection connection) {
        super(connection);
    }

    @Override
    protected String getTableName() {
        return null;
    }

    @Override
    public Optional<Author> getById(Long id) {
        return Optional.empty();
    }

    @Override
    public void save(Author item) {

    }

    @Override
    public void removeById(Long id) {

    }
}
