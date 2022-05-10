package com.epam.library.service.impl;

import com.epam.library.dao.DaoHelper;
import com.epam.library.dao.DaoHelperFactory;
import com.epam.library.dao.UserDao;
import com.epam.library.entity.User;
import com.epam.library.exception.DaoException;
import com.epam.library.exception.ServiceException;
import com.epam.library.service.AbstractService;
import com.epam.library.service.EntityService;
import com.epam.library.service.UserService;

import java.util.Optional;

public class UserServiceImpl extends AbstractService implements UserService, EntityService {

    public UserServiceImpl(DaoHelperFactory daoHelperFactory) {
        super(daoHelperFactory);
    }

    @Override
    public void signUp(User user) throws ServiceException {
        try (DaoHelper helper = daoHelperFactory.create()) {
            helper.startTransaction();
            UserDao dao = helper.createUserDao();
            dao.saveUser(user);
            helper.endTransaction();
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
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

    @Override
    public int calculateNumberOfRows() throws ServiceException {
        try (DaoHelper helper = daoHelperFactory.create()) {
            helper.startTransaction();
            UserDao dao = helper.createUserDao();
            int numberOfRows = calculateNumberOfRows(dao);
            helper.endTransaction();
            return numberOfRows;
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }
}
