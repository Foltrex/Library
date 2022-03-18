package com.epam.library.service.implementation;

import com.epam.library.dao.*;
import com.epam.library.entity.Book;
import com.epam.library.exception.DaoException;
import com.epam.library.exception.ServiceException;
import com.epam.library.service.AbstractService;
import com.epam.library.service.BookService;
import com.epam.library.service.EntityService;

import java.util.List;
import java.util.Optional;

public class BookServiceImpl extends AbstractService implements BookService, EntityService {

    public BookServiceImpl(DaoHelperFactory daoHelperFactory) {
        super(daoHelperFactory);
    }


    @Override
    public List<Book> getBooks() throws ServiceException {
        try (DaoHelper helper = daoHelperFactory.create()) {
            helper.startTransaction();
            BookDao dao = helper.createBookDao();
            List<Book> books = dao.getBooks();
            helper.endTransaction();
            return books;
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public Optional<Book> searchBookById(long id) throws ServiceException {
        try (DaoHelper helper = daoHelperFactory.create()) {
            helper.startTransaction();
            SearchBookDao dao = helper.createSearchBookDao();
            Optional<Book> book = dao.searchBookById(id);
            helper.endTransaction();
            return book;
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public void saveBook(Book book) throws ServiceException {
        try (DaoHelper helper = daoHelperFactory.create()) {
            helper.startTransaction();
            BookDao dao = helper.createBookDao();
            dao.saveBook(book);
            helper.endTransaction();
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public List<Book> findPartOfBooks(int currentPage, int recordsPerPage) throws ServiceException {
        try (DaoHelper helper = daoHelperFactory.create()) {
            helper.startTransaction();
            BookDao dao = helper.createBookDao();
            int startingPosition = currentPage * recordsPerPage - recordsPerPage;
            List<Book> books = dao.getBooksFromPosition(startingPosition, recordsPerPage);
            helper.endTransaction();
            return books;
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public List<Book> searchBooksFromPositionByAuthorId(Long id, int currentPage, int recordsPerPage) throws ServiceException {
        try (DaoHelper helper = daoHelperFactory.create()) {
            helper.startTransaction();
            SearchBookDao dao = helper.createSearchBookDao();
            int startingPosition = currentPage * recordsPerPage - recordsPerPage;
            List<Book> authorBooks = dao.searchBooksFromPositionByAuthorId(id, startingPosition, recordsPerPage);
            helper.endTransaction();
            return authorBooks;
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public List<Book> searchBooksFromPositionByGenreId(Long id, int currentPage, int recordsPerPage) throws ServiceException {
        try (DaoHelper helper = daoHelperFactory.create()) {
            helper.startTransaction();
            SearchBookDao dao = helper.createSearchBookDao();
            int startingPosition = currentPage * recordsPerPage - recordsPerPage;
            List<Book> genreBooks = dao.searchBooksFromPositionByGenreId(id, startingPosition, recordsPerPage);
            helper.endTransaction();
            return genreBooks;
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public List<Book> searchBooksFromPositionByBookTitle(String title, int currentPage, int recordsPerPage) throws ServiceException {
        try (DaoHelper helper = daoHelperFactory.create()) {
            helper.startTransaction();
            SearchBookDao dao = helper.createSearchBookDao();
            int startingPosition = currentPage * recordsPerPage - recordsPerPage;
            List<Book> authorBooks = dao.searchBooksFromPositionByBookTitle(title, startingPosition, recordsPerPage);
            helper.endTransaction();
            return authorBooks;
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public int calculateNumberOfRows() throws ServiceException {
        try (DaoHelper helper = daoHelperFactory.create()) {
            helper.startTransaction();
            BookDao dao = helper.createBookDao();
            int numberOfRows = calculateNumberOfRows(dao);
            helper.endTransaction();
            return numberOfRows;
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }
}
