package com.epam.library.dao;

import com.epam.library.exception.DaoException;

public interface EntityDao {
    int calculateNumberOfRows() throws DaoException;
}
