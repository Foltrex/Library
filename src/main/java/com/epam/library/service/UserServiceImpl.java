package com.epam.library.service;

import com.epam.library.dao.DaoHelper;
import com.epam.library.dao.DaoHelperFactory;
import com.epam.library.dao.UserDao;
import com.epam.library.entity.User;
import com.epam.library.exception.DaoException;
import com.epam.library.exception.ServiceException;

import java.util.Optional;

public class UserServiceImpl {

    private DaoHelperFactory daoHelperFactory;

    public UserServiceImpl(DaoHelperFactory daoHelperFactory) {
        this.daoHelperFactory = daoHelperFactory;
    }

    public Optional<User> login(String login, String password) throws ServiceException {
        try (DaoHelper helper = daoHelperFactory.create()) {
            helper.startTransaction();
            UserDao dao = helper.createUserDao();
            Optional<User> user = dao.findUserByLoginAndPassword(login, password);
            helper.endTransaction();
            return user;
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }
}
