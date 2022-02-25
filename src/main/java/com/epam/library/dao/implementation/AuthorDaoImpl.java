package com.epam.library.dao.implementation;

import com.epam.library.dao.AbstractDao;
import com.epam.library.entity.Author;
import com.epam.library.mapper.AuthorRowMapper;

import java.sql.Connection;
import java.util.LinkedHashMap;
import java.util.Map;

public class AuthorDaoImpl extends AbstractDao<Author> {

    public AuthorDaoImpl(Connection connection) {
        super(connection, new AuthorRowMapper(), Author.TABLE);
    }

    @Override
    protected Map<String, Object> extractFields(Author item) {
        // ImmutableMap.of (...) is more beautiful :(
        return new LinkedHashMap<String, Object>() {{
            put(Author.ID, item.getId());
            put(Author.NAME, item.getName());
            put(Author.SURNAME, item.getSurname());
        }};
    }
}
