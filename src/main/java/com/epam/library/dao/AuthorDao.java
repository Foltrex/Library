package com.epam.library.dao;

import com.epam.library.entity.Author;
import com.epam.library.entity.Book;
import com.epam.library.exception.DaoException;

import java.util.List;

public interface AuthorDao extends EntityDao {

    List<Author> getAuthors() throws DaoException;

    void saveAuthor(Author author) throws DaoException;
}
