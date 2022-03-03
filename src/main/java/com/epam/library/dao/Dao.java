package com.epam.library.dao;

import com.epam.library.entity.Identifable;
import com.epam.library.exception.DaoException;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

// CRUD
public interface Dao<T extends Identifable> {

    Optional<T> getById(long id) throws DaoException;

    List<T> getAll() throws DaoException;

    void save(T item) throws SQLException, DaoException;

    void removeById(Long id) throws SQLException, DaoException;
}
