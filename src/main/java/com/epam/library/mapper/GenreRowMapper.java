package com.epam.library.mapper;

import com.epam.library.dao.implemetation.GenreDaoImpl;
import com.epam.library.entity.Genre;

import java.sql.ResultSet;
import java.sql.SQLException;

public class GenreRowMapper implements RowMapper<Genre> {
    @Override
    public Genre map(ResultSet resultSet) throws SQLException {
        long id = resultSet.getLong(GenreDaoImpl.ID);
        String name = resultSet.getString(GenreDaoImpl.NAME);

        return new Genre(id, name);
    }
}
