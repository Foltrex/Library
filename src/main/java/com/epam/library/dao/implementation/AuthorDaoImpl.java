package com.epam.library.dao.implementation;

import com.epam.library.dao.AbstractDao;
import com.epam.library.dao.AuthorDao;
import com.epam.library.entity.Author;
import com.epam.library.exception.DaoException;
import com.epam.library.mapper.AuthorRowMapper;

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
        // ImmutableMap.of (...) is more beautiful :(
        return new LinkedHashMap<String, Object>() {{
            put(Author.NAME, item.getName());
            put(Author.SURNAME, item.getSurname());
        }};
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
