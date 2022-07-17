package com.epam.library.service.impl;

import com.epam.library.dao.*;
import com.epam.library.entity.Author;
import com.epam.library.exception.DaoException;
import com.epam.library.exception.ServiceException;
import com.epam.library.service.AbstractService;
import com.epam.library.service.AuthorService;
import com.epam.library.service.EntityService;

import java.util.List;

public class AuthorServiceImpl extends AbstractService implements AuthorService, EntityService {

    public AuthorServiceImpl(DaoHelperFactory daoHelperFactory) {
        super(daoHelperFactory);
    }

    @Override
    public List<Author> getAuthors() throws ServiceException {
        try (DaoHelper helper = daoHelperFactory.create()) {
            helper.startTransaction();
            AuthorDao dao = helper.createAuthorDao();
            List<Author> authors = dao.getAuthors();
            helper.endTransaction();
            return authors;
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public void saveAuthor(Author author) throws ServiceException {
        try (DaoHelper helper = daoHelperFactory.create()) {
            helper.startTransaction();
            AuthorDao dao = helper.createAuthorDao();
            dao.saveAuthor(author);
            helper.endTransaction();
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public int calculateNumberOfRows() throws ServiceException {
        try (DaoHelper helper = daoHelperFactory.create()) {
            helper.startTransaction();
            AuthorDao dao = helper.createAuthorDao();
            int numberOfRows = calculateNumberOfRows(dao);
            helper.endTransaction();
            return numberOfRows;
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }
}