package com.epam.library.dao;

import com.epam.library.connection.ConnectionPool;
import com.epam.library.connection.ProxyConnection;
import com.epam.library.dao.impl.*;
import com.epam.library.exception.DaoException;

import java.sql.SQLException;

/** Helps to perform transaction, creates dao object and returns connection to
 * {@link com.epam.library.connection.ConnectionPool}
 */
public class DaoHelper implements AutoCloseable {

    private final ProxyConnection connection;

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

    public SearchBookDao createSearchBookDao() {
        return new BookDaoImpl(connection);
    }

    public AuthorDao createAuthorDao() {
        return new AuthorDaoImpl(connection);
    }

    public GenreDao createGenreDao() {
        return new GenreDaoImpl(connection);
    }

    public BookRentalDao createBookRentalDao() {
        return new BookRentalDaoImpl(connection);
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
