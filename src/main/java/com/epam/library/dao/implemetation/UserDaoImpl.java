package com.epam.library.dao.implemetation;

import com.epam.library.dao.AbstractDao;
import com.epam.library.dao.UserDao;
import com.epam.library.entity.User;
import com.epam.library.exception.DaoException;
import com.epam.library.mapper.UserRowMapper;
import com.google.common.collect.ImmutableMap;
import org.apache.commons.codec.digest.DigestUtils;

import java.sql.*;
import java.util.*;

public class UserDaoImpl extends AbstractDao<User> implements UserDao {
    public static final String TABLE = "users";

    public static final String NAME = "name";
    public static final String SURNAME = "surname";
    public static final String PHONE_NUMBER = "phone_number";
    public static final String LOGIN = "login";
    public static final String PASSWORD = "password";
    public static final String ROLE = "role";

    private static final String SELECT_BY_LOGIN_AND_PASSWORD = "SELECT * FROM users WHERE login= ? AND password = MD5(?)";

    public UserDaoImpl(Connection connection) {
        super(connection, new UserRowMapper(), TABLE);
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

    // about saving the password during registration ?
    @Override
    protected Map<String, Object> extractFields(User item) {
        return ImmutableMap.of(
                ID,             item.getId(),
                NAME,           item.getName(),
                SURNAME,        item.getSurname(),
                PHONE_NUMBER,   item.getPhoneNumber(),
                LOGIN,          item.getLogin(),
                PASSWORD,       DigestUtils.md5Hex(item.getPassword()),
                ROLE,           item.getRole()
        );
    }
}
