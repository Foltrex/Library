package com.epam.library.service.impl;

import com.epam.library.dao.*;
import com.epam.library.entity.Role;
import com.epam.library.entity.User;
import com.epam.library.exception.DaoException;
import com.epam.library.exception.ServiceException;
import com.epam.library.service.AdminService;

import java.util.List;

public class AdminServiceImpl implements AdminService {

    private final DaoHelperFactory daoHelperFactory;

    public AdminServiceImpl(DaoHelperFactory daoHelperFactory) {
        this.daoHelperFactory = daoHelperFactory;
    }

    @Override
    public List<User> getUsers(Role role) throws ServiceException {
        try (DaoHelper helper = daoHelperFactory.create()) {
            helper.startTransaction();
            UserRoleDao dao = helper.createUserRoleDao();
            List<User> users = dao.findUsersByRole(role);
            helper.endTransaction();
            return users;
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public void changeUserBlocking(Long id, Boolean isBanned) throws ServiceException {
        try (DaoHelper helper = daoHelperFactory.create()) {
            helper.startTransaction();
            UserDao dao = helper.createUserDao();
            dao.updateUserBlockingById(id, isBanned);
            helper.endTransaction();
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }
}
