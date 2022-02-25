package com.epam.library.mapper;

import com.epam.library.entity.Role;
import com.epam.library.entity.User;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserRowMapper implements RowMapper<User> {
    @Override
    public User map(ResultSet resultSet) throws SQLException {
        long id = resultSet.getLong(User.ID);
        String name = resultSet.getString(User.NAME);
        String surname = resultSet.getString(User.SURNAME);
        String phoneNumber = resultSet.getString(User.PHONE_NUMBER);
        String login = resultSet.getString(User.LOGIN);
        String password = resultSet.getString(User.PASSWORD);

        String roleString = resultSet.getString(User.ROLE);
        Role role = Role.valueOf(roleString.toUpperCase());

        return new User(id, name, surname, phoneNumber, login, password, role);
    }
}
