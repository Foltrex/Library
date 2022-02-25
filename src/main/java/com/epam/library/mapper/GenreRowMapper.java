package com.epam.library.mapper;

import com.epam.library.dao.implementation.GenreDaoImpl;
import com.epam.library.entity.Genre;

import java.sql.ResultSet;
import java.sql.SQLException;

public class GenreRowMapper implements RowMapper<Genre> {
    @Override
    public Genre map(ResultSet resultSet) throws SQLException {
        long id = resultSet.getLong(Genre.ID);
        String name = resultSet.getString(Genre.NAME);

        return new Genre(id, name);
    }
}
