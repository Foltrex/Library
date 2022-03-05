package com.epam.library.dao;

import com.epam.library.entity.User;
import com.epam.library.exception.DaoException;

import java.util.Optional;

public interface UserDao {
    Optional<User> findUserByLoginAndPassword(String login, String password) throws DaoException;

    void updateUserBlockingById(Long id, Boolean isBanned) throws DaoException;
}
