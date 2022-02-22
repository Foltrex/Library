package com.epam.library.mapper;

import com.epam.library.dao.implemetation.AuthorDaoImpl;
import com.epam.library.dao.implemetation.BookDaoImpl;
import com.epam.library.dao.implemetation.GenreDaoImpl;
import com.epam.library.dao.implemetation.UserDaoImpl;
import com.epam.library.entity.*;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface RowMapper<T extends Identifable> {

    T map(ResultSet resultSet) throws SQLException;

    static RowMapper<? extends Identifable> create(String table) {
        switch (table) {
            case UserDaoImpl.TABLE:
                return new UserRowMapper();
            case BookDaoImpl.TABLE:
                return new BookRowMapper();
            case AuthorDaoImpl.TABLE:
                return new AuthorRowMapper();
            case GenreDaoImpl.TABLE:
                return new GenreRowMapper();
            default:
                throw new IllegalArgumentException("Unknown table = " + table);
        }
    }
}
