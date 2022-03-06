package com.epam.library.dao.implementation;

import com.epam.library.dao.AbstractDao;
import com.epam.library.dao.BookDao;
import com.epam.library.entity.Book;
import com.epam.library.exception.DaoException;
import com.epam.library.mapper.BookRowMapper;

import java.io.Serializable;
import java.sql.Connection;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class BookDaoImpl extends AbstractDao<Book> implements BookDao {

    private static final String SELECT_BOOKS = String.format("%s %s %s %s %s ",
            "SELECT books.id, title, author_id, authors.name AS author_name, surname AS author_surname,",
            "stock, genre_id, genres.name AS genre_name",
            "FROM books",
            "INNER JOIN authors ON author_id = authors.id",
            "INNER JOIN genres ON genre_id = genres.id");

    private static final String SELECT_BOOKS_BY_TITLE = String.format("%s %s", SELECT_BOOKS, "WHERE title=?");


    public BookDaoImpl(Connection connection) {
        super(connection, new BookRowMapper(), Book.TABLE);
    }

    @Override
    public List<Book> getBooks() throws DaoException {
        return executeQuery(SELECT_BOOKS);
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
    public List<Book> searchBooksByAuthorId(long id) throws DaoException {
        String condition = "WHERE author_id = ?";
        return executeQuery(SELECT_BOOKS + condition, id);
    }

    @Override
    public List<Book> searchBooksByGenreId(Long id) throws DaoException {
        String condition = "WHERE genre_id = ?";
        return executeQuery(SELECT_BOOKS + condition, id);
    }

    @Override
    public List<Book> searchBooksByTitle(String title) throws DaoException {
        return title != null && !title.isEmpty() ? executeQuery(SELECT_BOOKS_BY_TITLE, title)
                                                 : executeQuery(SELECT_BOOKS);
    }


    @Override
    protected Map<String, Object> extractFields(Book item) {
        // ImmutableMap.of (...) is more beautiful :(
        return new LinkedHashMap<String, Object>() {{
            put(Book.TITLE, item.getTitle());
            put(Book.AUTHOR_ID, item.getAuthor().getId());
            put(Book.STOCK, item.getStock());
            put(Book.GENRE_ID, item.getGenre().getId());
        }};
    }
}
