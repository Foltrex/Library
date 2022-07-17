package com.epam.library.service;

import com.epam.library.dao.AbstractDao;
import com.epam.library.dao.DaoHelper;
import com.epam.library.dao.DaoHelperFactory;
import com.epam.library.dao.EntityDao;
import com.epam.library.entity.Identifable;
import com.epam.library.entity.User;
import com.epam.library.exception.DaoException;
import com.epam.library.exception.ServiceException;
import com.epam.library.service.EntityService;

import java.util.List;
import java.util.Optional;

public abstract class AbstractService implements EntityService {

    protected final DaoHelperFactory daoHelperFactory;

    public AbstractService(DaoHelperFactory daoHelperFactory) {
        this.daoHelperFactory = daoHelperFactory;
    }

    protected int calculateNumberOfRows(EntityDao dao) throws ServiceException {
        try {
            return dao.calculateNumberOfRows();
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }
}
