package com.epam.library.dao.implemetation;

import com.epam.library.dao.AbstractDao;
import com.epam.library.dao.UserDao;
import com.epam.library.entity.User;
import com.epam.library.exception.DaoException;
import com.epam.library.mapper.UserRowMapper;

import java.sql.*;
import java.util.Optional;

public class UserDaoImpl extends AbstractDao<User> implements UserDao {

    private static final String SELECT_BY_LOGIN_AND_PASSWORD = "SELECT * FROM users WHERE login= ? AND password = MD5(?)";
    private static final String SAVE_USER = "INSERT INTO users(name,surname,phone_number,login,password,role)" +
            "VALUES(?,?,?,?,?,?);";

    public UserDaoImpl(Connection connection) {
        super(connection);
    }

    @Override
    public Optional<User> findUserByLoginAndPassword(String login, String password) throws DaoException {
        return executeForSingleResult(
                SELECT_BY_LOGIN_AND_PASSWORD,
                new UserRowMapper(),
                login,
                password
        );
    }

    @Override
    public void save(User item) throws SQLException {
        PreparedStatement statement = createStatement(SAVE_USER, item.getName(), item.getSurname(),
                item.getPhoneNumber(), item.getLogin(), item.getPassword(), item.getRole());
        statement.executeUpdate();
    }

    @Override
    protected String getTableName() {
        return User.TABLE;
    }
}
