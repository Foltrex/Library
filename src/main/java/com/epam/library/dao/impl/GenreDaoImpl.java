package com.epam.library.dao.impl;

import com.epam.library.dao.AbstractDao;
import com.epam.library.dao.GenreDao;
import com.epam.library.entity.Genre;
import com.epam.library.exception.DaoException;
import com.epam.library.mapper.impl.GenreRowMapper;

import java.sql.Connection;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class GenreDaoImpl extends AbstractDao<Genre> implements GenreDao {

    private static final String SELECT_ALL_FROM_POSITION = "SELECT * FROM genres LIMIT ? OFFSET ?";

    public GenreDaoImpl(Connection connection) {
        super(connection, new GenreRowMapper(), Genre.TABLE);
    }

    @Override
    protected Map<String, Object> extractFields(Genre item) {
        // ImmutableMap.of (...) is more beautiful :(
        return new LinkedHashMap<>() {{
            put(Genre.NAME, item.getName());
        }};
    }

    @Override
    public List<Genre> getGenres() throws DaoException {
        return getAll();
    }

    @Override
    public List<Genre> getGenresFromPosition(int startingPosition, int recordsPerPage) throws DaoException {
        return executeQuery(SELECT_ALL_FROM_POSITION, recordsPerPage, startingPosition);
    }

    @Override
    public void saveGenre(Genre genre) throws DaoException {
        save(genre);
    }
}
