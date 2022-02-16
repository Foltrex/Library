package com.epam.library.dao;

import com.epam.library.entity.Role;
import com.epam.library.entity.User;
import com.epam.library.exception.DaoException;

import java.sql.*;
import java.util.Optional;

public class SimpleUserDao implements UserDao {

    // add better realization
    public Optional<User> findUserByLoginAndPassword(final String login, final String password) throws DaoException {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection connection = DriverManager.getConnection("url of a table", "user", "password");
            try (PreparedStatement statement = connection.prepareStatement("SELECT ID, LOGIN FROM USER WHERE LOGIN=? AND PASSWORD = MD5(?)")) {
                statement.setString(1, login);
                statement.setString(2, password);
                try (ResultSet resultSet = statement.executeQuery()) {
                    if (resultSet.next()) {
                        long userId = resultSet.getLong("id");
                        String userName = resultSet.getString("name");
                        String userSurname = resultSet.getString("surname");
                        String userPhoneNumber = resultSet.getString("phone_number");
                        String userLogin = resultSet.getString("login");
                        String userPassword = resultSet.getString("password");

                        String roleLine = resultSet.getString("role");
                        Role userRole = Role.valueOf(roleLine);

                        return Optional.of(new User(userId, userName, userSurname, userPhoneNumber, userLogin, userPassword, userRole));
                    }
                }
            }
            return Optional.empty();

        } catch (ClassNotFoundException | SQLException e) {
            throw new DaoException(e);
        }
    }
}
