package com.epam.library.service;

import com.epam.library.dao.UserDao;
import com.epam.library.entity.User;
import com.epam.library.exception.DaoException;
import com.epam.library.exception.ServiceException;
import java.util.Optional;

public class SimpleUserService implements UserService {
    private UserDao dao;

    public SimpleUserService(UserDao dao) {
        this.dao = dao;
    }

    public Optional<User> login(String login, String password) throws ServiceException {
        try {
            Optional<User> user = dao.findUserByLoginAndPassword(login, password);
            return user;
        } catch (DaoException exception) {
            throw new ServiceException(exception);
        }
    }
}
