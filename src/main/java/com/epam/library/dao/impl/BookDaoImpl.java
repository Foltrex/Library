package com.epam.library.dao.impl;

import com.epam.library.dao.AbstractDao;
import com.epam.library.dao.BookDao;
import com.epam.library.dao.SearchBookDao;
import com.epam.library.entity.Book;
import com.epam.library.exception.DaoException;
import com.epam.library.mapper.impl.BookRowMapper;
import com.google.common.collect.ImmutableMap;

import java.sql.Connection;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class BookDaoImpl extends AbstractDao<Book> implements BookDao, SearchBookDao {

    private static final String SELECT_BOOKS = String.format("%s %s %s %s %s ",
            "SELECT books.id, title, author_id, authors.name AS author_name, surname AS author_surname,",
            "stock, genre_id, genres.name AS genre_name",
            "FROM books",
            "INNER JOIN authors ON author_id = authors.id",
            "INNER JOIN genres ON genre_id = genres.id");

    private static final String SELECT_BOOKS_BY_TITLE = String.format("%s %s", SELECT_BOOKS, "WHERE title=?");
    private static final String SELECT_BOOKS_AUTHOR_ID = String.format("%s %s", SELECT_BOOKS, "WHERE author_id = ?");
    private static final String SELECT_BOOKS_GENRE_ID = String.format("%s %s", SELECT_BOOKS, "WHERE genre_id = ?");

    private static final String UPDATE_BOOK_STOCK_DEC = String.format(
            "UPDATE %s SET %s = %s - 1 WHERE id = ?;", Book.TABLE, Book.STOCK, Book.STOCK);

    private static final String UPDATE_BOOK_STOCK_INC = String.format(
            "UPDATE %s SET %s = %s + 1 WHERE id = ?;", Book.TABLE, Book.STOCK, Book.STOCK);

    private static final String LIMIT = " LIMIT ?";
    private static final String OFFSET = " OFFSET ?";


    public BookDaoImpl(Connection connection) {
        super(connection, new BookRowMapper(), Book.TABLE);
    }

    @Override
    public List<Book> getBooks() throws DaoException {
        return executeQuery(SELECT_BOOKS);
    }

    @Override
    public List<Book> getBooksFromPosition(int startingPosition, int recordsPerPage) throws DaoException {
        String query = SELECT_BOOKS + LIMIT + OFFSET;
        return executeQuery(query, recordsPerPage, startingPosition);
    }

    @Override
    public void borrowBook(Long id) throws DaoException {
        executeUpdate(UPDATE_BOOK_STOCK_DEC, id);
    }

    @Override
    public void returnBook(Long id) throws DaoException {
        executeUpdate(UPDATE_BOOK_STOCK_INC, id);
    }

    @Override
    public Optional<Book> searchBookById(long id) throws DaoException {
        String condition = String.format("WHERE %s.id = ?", Book.TABLE);
        return executeForSingleResult(SELECT_BOOKS + condition, id);
    }

    @Override
    public void saveBook(Book book) throws DaoException {
        save(book);
    }

    @Override
    public List<Book> searchBooksFromPositionByAuthorId(Long id, int startingPosition, int recordsPerPage) throws DaoException {
        return executeQuery(SELECT_BOOKS_AUTHOR_ID + LIMIT + OFFSET, id, recordsPerPage, startingPosition);
    }

    @Override
    public List<Book> searchBooksFromPositionByGenreId(Long id, int startingPosition, int recordsPerPage) throws DaoException {
        return executeQuery(SELECT_BOOKS_GENRE_ID + LIMIT + OFFSET, id, recordsPerPage, startingPosition);
    }

    @Override
    public List<Book> searchBooksFromPositionByBookTitle(String title, int startingPosition, int recordsPerPage) throws DaoException {
        return executeQuery(SELECT_BOOKS_BY_TITLE + LIMIT + OFFSET, title, recordsPerPage, startingPosition);
    }

    @Override
    protected Map<String, Object> extractFields(Book item) {
        return ImmutableMap.of(
                Book.TITLE, item.getTitle(),
                Book.AUTHOR_ID, item.getAuthor().getId(),
                Book.STOCK, item.getStock(),
                Book.GENRE_ID, item.getGenre().getId()
        );
    }
}