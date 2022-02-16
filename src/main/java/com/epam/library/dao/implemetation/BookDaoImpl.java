package com.epam.library.dao.implemetation;

import com.epam.library.dao.AbstractDao;
import com.epam.library.entity.Book;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class BookDaoImpl extends AbstractDao<Book> {

    private static final String SAVE_BOOK = "INSERT INTO books(title, stock, genre_id) VALUES(?,?,?);";

    public BookDaoImpl(Connection connection) {
        super(connection);
    }

    @Override
    public void save(Book item) throws SQLException {
        PreparedStatement statement = createStatement(SAVE_BOOK, item.getTitle(), item.getStock(), item.getGenreId());
        statement.executeUpdate();
    }

    @Override
    protected String getTableName() {
        return Book.TABLE;
    }

}
