package com.epam.library.dao.implemetation;

import com.epam.library.dao.AbstractDao;
import com.epam.library.entity.Book;

import java.sql.Connection;
import java.util.List;
import java.util.Optional;

public class BookDao extends AbstractDao<Book> {

    public BookDao(Connection connection) {
        super(connection);
    }

    @Override
    protected String getTableName() {
        return null;
    }

    @Override
    public Optional<Book> getById(Long id) {
        return Optional.empty();
    }

    @Override
    public void save(Book item) {

    }

    @Override
    public void removeById(Long id) {

    }
}
