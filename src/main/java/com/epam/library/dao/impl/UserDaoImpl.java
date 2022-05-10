package com.epam.library.dao.impl;

import com.epam.library.dao.AbstractDao;
import com.epam.library.dao.UserDao;
import com.epam.library.dao.UserRoleDao;
import com.epam.library.entity.Role;
import com.epam.library.entity.User;
import com.epam.library.exception.DaoException;
import com.epam.library.mapper.impl.UserRowMapper;
import org.apache.commons.codec.digest.DigestUtils;

import java.sql.*;
import java.util.*;

public class UserDaoImpl extends AbstractDao<User> implements UserDao, UserRoleDao {

    private static final String SELECT_BY_LOGIN_AND_PASSWORD = "SELECT * FROM users WHERE login = ? AND password = MD5(?)";
    private static final String SELECT_BY_ROLE = "SELECT * FROM users WHERE role = ?";

    private static final String UPDATE_USER_BLOCKING = "UPDATE users SET is_banned = ? WHERE id = ?";

    public UserDaoImpl(Connection connection) {
        super(connection, new UserRowMapper(), User.TABLE);
    }

    @Override
    public Optional<User> findUserByLoginAndPassword(String login, String password) throws DaoException {
        return executeForSingleResult(
                SELECT_BY_LOGIN_AND_PASSWORD,
                login,
                password
        );
    }

    @Override
    public void saveUser(User user) throws DaoException {
        save(user);
    }

    @Override
    public void updateUserBlockingById(Long id, Boolean isBanned) throws DaoException {
        executeUpdate(UPDATE_USER_BLOCKING, isBanned, id);
    }

    @Override
    public List<User> findUsersByRole(Role role) throws DaoException {
        return executeQuery(
                SELECT_BY_ROLE,
                role.getRoleName()
        );
    }

    @Override
    protected Map<String, Object> extractFields(User item) {
        // ImmutableMap.of (...) is more beautiful :(
        return new LinkedHashMap<>() {{
           put(User.NAME, item.getName());
           put(User.SURNAME, item.getSurname());
           put(User.PHONE_NUMBER, item.getPhoneNumber());
           put(User.LOGIN, item.getLogin());
           put(User.PASSWORD, DigestUtils.md5Hex(item.getPassword()));
           put(User.ROLE, item.getRole().getRoleName());
           put(User.IS_BANNED, item.isBanned());
        }};
    }
}
