package com.epam.library.mapper;

import com.epam.library.dao.implemetation.UserDaoImpl;
import com.epam.library.entity.Role;
import com.epam.library.entity.User;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserRowMapper implements RowMapper<User> {
    @Override
    public User map(ResultSet resultSet) throws SQLException {
        long id = resultSet.getLong(UserDaoImpl.ID);
        String name = resultSet.getString(UserDaoImpl.NAME);
        String surname = resultSet.getString(UserDaoImpl.SURNAME);
        String phoneNumber = resultSet.getString(UserDaoImpl.PHONE_NUMBER);
        String login = resultSet.getString(UserDaoImpl.LOGIN);
        String password = resultSet.getString(UserDaoImpl.PASSWORD);

        String roleString = resultSet.getString(UserDaoImpl.ROLE);
        Role role = Role.valueOf(roleString.toUpperCase());

        return new User(id, name, surname, phoneNumber, login, password, role);
    }
}
