package com.epam.library.dao;

import com.epam.library.entity.Role;
import com.epam.library.entity.User;
import com.epam.library.exception.DaoException;
import com.epam.library.mapper.RowMapper;
import com.epam.library.mapper.UserRowMapper;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UserDaoImpl extends AbstractDao<User> implements UserDao {

    public static final String SELECT_ALL_USERS = "SELECT * FROM users";
    public static final String SELECT_USER_BY_ID = "SELECT * FROM users WHERE id=?";
    public static final String SELECT_BY_LOGIN_AND_PASSWORD = "SELECT * FROM users WHERE login=? and password=?";

    public UserDaoImpl(Connection connection) {
        super(connection);
    }

    @Override
    public Optional<User> findUserByLoginAndPassword(String login, String password) throws DaoException {
        return executeForSingleResult(
                SELECT_BY_LOGIN_AND_PASSWORD,
                new UserRowMapper(),
                login,
                password);
    }

    @Override
    protected List<User> executeQuery(String query, RowMapper<User> mapper, Object... params) throws DaoException {
        return super.executeQuery(query, mapper, params);
    }

    @Override
    public Optional<User> getById(Long id) {
        return Optional.empty();
    }

    @Override
    public List<User> getAll() throws DaoException {
        return executeQuery(SELECT_ALL_USERS, new UserRowMapper());
    }

    @Override
    public void save(User item) {

    }

    @Override
    public void removeById(Long id) {

    }

    @Override
    protected Optional<User> executeForSingleResult(String query, RowMapper<User> mapper, Object... params) throws DaoException {
        return super.executeForSingleResult(query, mapper, params);
    }

    @Override
    protected String getTableName() {
        return User.TABLE;
    }
}
