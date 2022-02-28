package com.epam.library.dao;

import com.epam.library.connection.ConnectionPool;
import com.epam.library.connection.ProxyConnection;
import com.epam.library.dao.implementation.AuthorDaoImpl;
import com.epam.library.dao.implementation.BookDaoImpl;
import com.epam.library.dao.implementation.GenreDaoImpl;
import com.epam.library.dao.implementation.UserDaoImpl;
import com.epam.library.entity.Author;
import com.epam.library.entity.Book;
import com.epam.library.entity.Genre;
import com.epam.library.entity.User;
import com.epam.library.exception.DaoException;

import java.sql.SQLException;

public class DaoHelper implements AutoCloseable {

    private ProxyConnection connection;

    public DaoHelper(ConnectionPool pool) {
        this.connection = pool.getConnection();
    }


    public UserDao createUserDao() {
        return new UserDaoImpl(connection);
    }

    public UserRoleDao createUserRoleDao() {
        return new UserDaoImpl(connection);
    }

    public BookDao createBookDao() {
        return new BookDaoImpl(connection);
    }

    public AuthorDao createAuthorDao() {
        return new AuthorDaoImpl(connection);
    }

    public GenreDao createGenreDao() {
        return new GenreDaoImpl(connection);
    }


    @Override
    public void close() {
        connection.close();
    }

    public void startTransaction() throws DaoException {
        try {
            connection.setAutoCommit(false);
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    public void endTransaction() throws DaoException {
        try {
            connection.commit();
            connection.setAutoCommit(true);
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }
}
