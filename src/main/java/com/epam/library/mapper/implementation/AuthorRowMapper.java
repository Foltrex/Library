package com.epam.library.mapper.implementation;

import com.epam.library.entity.Author;
import com.epam.library.mapper.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class AuthorRowMapper implements RowMapper<Author> {
    @Override
    public Author map(ResultSet resultSet) throws SQLException {
        long id = resultSet.getLong(Author.ID);
        String name = resultSet.getString(Author.NAME);
        String surname = resultSet.getString(Author.SURNAME);

        return new Author(id, name, surname);
    }
}
