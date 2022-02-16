package com.epam.library.mapper;

import com.epam.library.entity.Role;
import com.epam.library.entity.User;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserRowMapper implements RowMapper<User> {
    @Override
    public User map(ResultSet resultSet) throws SQLException {
        long id = resultSet.getLong("id");
        String name = resultSet.getString("name");
        String surname = resultSet.getString("surname");
        String phoneNumber = resultSet.getString("phone_number");
        String login = resultSet.getString("login");
        String password = resultSet.getString("password");

        String roleString = resultSet.getString("role");
        Role role = Role.valueOf(roleString.toUpperCase());

        return new User(id, name, surname, phoneNumber, login, password, role);
    }
}
