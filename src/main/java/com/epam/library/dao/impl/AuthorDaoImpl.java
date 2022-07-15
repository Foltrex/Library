package com.epam.library.dao.impl;

import com.epam.library.dao.AbstractDao;
import com.epam.library.dao.AuthorDao;
import com.epam.library.entity.Author;
import com.epam.library.exception.DaoException;
import com.epam.library.mapper.impl.AuthorRowMapper;
import com.google.common.collect.ImmutableMap;

import java.sql.Connection;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class AuthorDaoImpl extends AbstractDao<Author> implements AuthorDao {

    public AuthorDaoImpl(Connection connection) {
        super(connection, new AuthorRowMapper(), Author.TABLE);
    }

    @Override
    protected Map<String, Object> extractFields(Author item) {
        return ImmutableMap.of(
                Author.NAME, item.getName(),
                Author.SURNAME, item.getSurname()
        );
    }

    @Override
    public List<Author> getAuthors() throws DaoException {
        return getAll();
    }

    @Override
    public void saveAuthor(Author author) throws DaoException {
        save(author);
    }
}
