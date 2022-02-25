package com.epam.library.dao.implementation;

import com.epam.library.dao.AbstractDao;
import com.epam.library.entity.Genre;
import com.epam.library.mapper.GenreRowMapper;

import java.sql.Connection;
import java.util.LinkedHashMap;
import java.util.Map;

public class GenreDaoImpl extends AbstractDao<Genre> {

    public GenreDaoImpl(Connection connection) {
        super(connection, new GenreRowMapper(), Genre.TABLE);
    }

    @Override
    protected Map<String, Object> extractFields(Genre item) {
        // ImmutableMap.of (...) is more beautiful :(
        return new LinkedHashMap<String, Object>() {{
            put(Genre.ID, item.getId());
            put(Genre.NAME, item.getName());
        }};
    }
}
