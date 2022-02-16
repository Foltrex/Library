package com.epam.library.mapper;

import com.epam.library.entity.*;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface RowMapper<T extends Identifable> {

    T map(ResultSet resultSet) throws SQLException;

    static RowMapper<? extends Identifable> create(String table) {
        switch (table) {
            case User.TABLE:
                return new UserRowMapper();
            case Book.TABLE:
                return new BookRowMapper();
            case Author.TABLE:
                return new AuthorRowMapper();
            case Genre.TABLE:
                return new GenreRowMapper();
            default:
                throw new IllegalArgumentException("Unknown table = " + table);
        }
    }
}
