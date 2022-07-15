package com.epam.library.mapper.impl;

import com.epam.library.entity.Genre;
import com.epam.library.mapper.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/** Maps result set to {@link com.epam.library.entity.Genre} */
public class GenreRowMapper implements RowMapper<Genre> {
    @Override
    public Genre map(ResultSet resultSet) throws SQLException {
        long id = resultSet.getLong(Genre.ID);
        String name = resultSet.getString(Genre.NAME);

        return new Genre(id, name);
    }
}
