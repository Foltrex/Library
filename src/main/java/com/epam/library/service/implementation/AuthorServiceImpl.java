package com.epam.library.service.implementation;

import com.epam.library.dao.AuthorDao;
import com.epam.library.dao.BookDao;
import com.epam.library.dao.DaoHelper;
import com.epam.library.dao.DaoHelperFactory;
import com.epam.library.entity.Author;
import com.epam.library.entity.Book;
import com.epam.library.exception.DaoException;
import com.epam.library.exception.ServiceException;
import com.epam.library.service.AuthorService;

import java.util.List;

public class AuthorServiceImpl implements AuthorService {


    private final DaoHelperFactory daoHelperFactory;

    public AuthorServiceImpl(DaoHelperFactory daoHelperFactory) {
        this.daoHelperFactory = daoHelperFactory;
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
}
