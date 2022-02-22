package com.epam.library.dao.implemetation;

import com.epam.library.dao.AbstractDao;
import com.epam.library.entity.Author;
import com.epam.library.mapper.AuthorRowMapper;
import com.google.common.collect.ImmutableMap;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Map;

public class AuthorDaoImpl extends AbstractDao<Author> {

    public static final String TABLE = "authors";

    public static final String NAME = "name";
    public static final String SURNAME = "surname";

    private static final String SAVE_AUTHOR = "INSERT INTO authors(name, surname) VALUES(?,?);";

    public AuthorDaoImpl(Connection connection) {
        super(connection, new AuthorRowMapper(), TABLE);
    }

    @Override
    protected Map<String, Object> extractFields(Author item) {
        return ImmutableMap.of(
                ID,         item.getId(),
                NAME,       item.getName(),
                SURNAME,    item.getSurname()
        );
    }
}
