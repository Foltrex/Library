package com.epam.library.dao.implemetation;

import com.epam.library.dao.AbstractDao;
import com.epam.library.entity.Genre;
import com.epam.library.mapper.GenreRowMapper;
import com.google.common.collect.ImmutableMap;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Map;
import java.util.Optional;

public class GenreDaoImpl extends AbstractDao<Genre> {

    public static final String TABLE = "genres";

    public static final String NAME = "name";

    private static final String SAVE_GENRE = "INSERT INTO genres(name) VALUES(?)";

    public GenreDaoImpl(Connection connection) {
        super(connection, new GenreRowMapper(), TABLE);
    }

    @Override
    protected Map<String, Object> extractFields(Genre item) {
        return ImmutableMap.of(
                ID,     item.getId(),
                NAME,   item.getName()
        );
    }
}
