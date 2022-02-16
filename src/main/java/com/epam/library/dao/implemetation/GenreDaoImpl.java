package com.epam.library.dao.implemetation;

import com.epam.library.dao.AbstractDao;
import com.epam.library.entity.Genre;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Optional;

public class GenreDaoImpl extends AbstractDao<Genre> {

    private static final String SAVE_GENRE = "INSERT INTO genres(name) VALUES(?)";

    public GenreDaoImpl(Connection connection) {
        super(connection);
    }

    @Override
    public void save(Genre item) throws SQLException {
        PreparedStatement statement = createStatement(SAVE_GENRE, item.getName());
        statement.executeUpdate();
    }

    @Override
    protected String getTableName() {
        return Genre.TABLE;
    }
}
