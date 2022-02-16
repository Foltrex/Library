package com.epam.library.dao.implemetation;

import com.epam.library.dao.AbstractDao;
import com.epam.library.entity.Author;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class AuthorDaoImpl extends AbstractDao<Author> {

    private static final String SAVE_AUTHOR = "INSERT INTO authors(name, surname) VALUES(?,?);";

    public AuthorDaoImpl(Connection connection) {
        super(connection);
    }

    @Override
    public void save(Author item) throws SQLException {
        PreparedStatement statement = createStatement(SAVE_AUTHOR, item.getName(), item.getSurname());
        statement.executeUpdate();
    }

    @Override
    protected String getTableName() {
        return Author.TABLE;
    }
}
