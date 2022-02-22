package com.epam.library.mapper;

import com.epam.library.dao.implemetation.AuthorDaoImpl;
import com.epam.library.entity.Author;

import java.sql.ResultSet;
import java.sql.SQLException;

public class AuthorRowMapper implements RowMapper<Author> {
    @Override
    public Author map(ResultSet resultSet) throws SQLException {
        long id = resultSet.getLong(AuthorDaoImpl.ID);
        String name = resultSet.getString(AuthorDaoImpl.NAME);
        String surname = resultSet.getString(AuthorDaoImpl.SURNAME);

        return new Author(id, name, surname);
    }
}
