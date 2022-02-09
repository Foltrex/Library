package com.epam.library.data.dao;

import com.epam.library.models.Role;
import com.epam.library.models.User;

import javax.naming.NamingException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class UserDAO extends AbstractDAO<User> {

    public UserDAO(Connection connection) {
        super(connection);
    }

    public static final String SQL_SELECT_ALL_USERS = "SELECT * FROM users";
    public static final String SQL_SELECT_USER_ID = "SELECT * FROM users WHERE id=?";

    public static final String SQL_SELECT_LOGIN = "SELECT * FROM users WHERE login=?";

    @Override
    public List<User> findAll() {
        List<User> users = new ArrayList<>();

        try (Statement statement = connection.createStatement()) {
            ResultSet rs = statement.executeQuery(SQL_SELECT_ALL_USERS);
            while (rs.next()) {

                int userId = rs.getInt("id");
                String userName = rs.getString("name");
                String userSurname = rs.getString("surname");
                String userPhoneNumber = rs.getString("phone_number");
                String userLogin = rs.getString("login");
                String userPassword = rs.getString("password");

                String roleString = rs.getString("role");
                Role userRole = Role.valueOf(roleString.toUpperCase());

                User user = new User(userId, userName, userSurname, userPhoneNumber, userLogin, userPassword, userRole);
                users.add(user);
            }
        } catch (SQLException /*| NamingException*/ e) {
            e.printStackTrace();
        }
        return users;
    }

    @Override
    public User findEntityById(int id) {
        User user = null;

        try (PreparedStatement statement = connection.prepareStatement(SQL_SELECT_USER_ID)) {
            statement.setInt(1, id);
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                // TODO: add getting fields value
                //user = new User();
            }
        } catch (SQLException /*| NamingException*/ e) {
            e.printStackTrace();
        }
        return user;
    }

    public User findUserByLogin(String login) {
        User user = null;

        try (PreparedStatement statement = connection.prepareStatement(SQL_SELECT_LOGIN)) {
            statement.setString(1, login);
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                int userId = rs.getInt("id");
                String userName = rs.getString("name");
                String userSurname = rs.getString("surname");
                String userPhoneNumber = rs.getString("phone_number");
                String userLogin = rs.getString("login");
                String userPassword = rs.getString("password");

                String roleString = rs.getString("role");
                Role userRole = Role.valueOf(roleString.toUpperCase());

                user = new User(userId, userName, userSurname, userPhoneNumber, userLogin, userPassword, userRole);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return user;
    }

    @Override
    public boolean delete(int id) {
        return false;
    }

    @Override
    public boolean delete(User entity) {
        return false;
    }

    @Override
    public boolean create(User entity) {
        return false;
    }

    @Override
    public User update(User entity) {
        return null;
    }


}
