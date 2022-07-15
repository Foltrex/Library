package com.epam.library.mapper.impl;

import com.epam.library.entity.Role;
import com.epam.library.entity.User;
import com.epam.library.mapper.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/** Maps result set to {@link com.epam.library.entity.User} */
public class UserRowMapper implements RowMapper<User> {
    @Override
    public User map(ResultSet resultSet) throws SQLException {
        Long id = resultSet.getLong(User.ID);
        String name = resultSet.getString(User.NAME);
        String surname = resultSet.getString(User.SURNAME);
        String phoneNumber = resultSet.getString(User.PHONE_NUMBER);
        String login = resultSet.getString(User.LOGIN);
        String password = resultSet.getString(User.PASSWORD);

        String roleString = resultSet.getString(User.ROLE);
        Role role = Role.valueOfRoleName(roleString);

        boolean isBanned = resultSet.getBoolean(User.IS_BANNED);

        return User.builder()
                .id(id)
                .name(name)
                .surname(surname)
                .phoneNumber(phoneNumber)
                .login(login)
                .password(password)
                .role(role)
                .isBanned(isBanned)
                .build();
    }
}
