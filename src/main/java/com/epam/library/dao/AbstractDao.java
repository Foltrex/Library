package com.epam.library.dao;

import com.epam.library.entity.Entity;
import com.epam.library.entity.Identifable;
import com.epam.library.exception.DaoException;
import com.epam.library.mapper.RowMapper;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public abstract class AbstractDao<T extends Identifable> implements Dao<T> {

    private final Connection connection;

    protected AbstractDao(Connection connection) {
        this.connection = connection;
    }

    protected List<T> executeQuery(String query, RowMapper<T> mapper, Object... params) throws DaoException {
        try (PreparedStatement statement = createStatement(query, params)) {
            ResultSet resultSet = statement.executeQuery();
            List<T> entities = new ArrayList<>();
            while (resultSet.next()) {
                T entity = mapper.map(resultSet);
                entities.add(entity);
            }

            return entities;
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public Optional<T> getById(long id) throws DaoException {
        String table = getTableName();
        RowMapper<T> mapper = (RowMapper<T>) RowMapper.create(table);
        String query = "SELECT * FROM " + table + " WHERE id=?;";
        return executeForSingleResult(query, mapper, id);
    }

    public List<T> getAll() throws DaoException {
        String table = getTableName();
        RowMapper<T> mapper = (RowMapper<T>) RowMapper.create(table);
        return executeQuery("SELECT * FROM " + table + ";", mapper);
    }

    @Override
    public void removeById(Long id) throws SQLException {
        PreparedStatement statement = createStatement("DELETE FROM " + getTableName() + " WHERE id=?;", id);
        statement.executeUpdate();
    }

    protected Optional<T> executeForSingleResult(String query, RowMapper<T> mapper, Object... params) throws DaoException {
        List<T> items = executeQuery(query, mapper, params);
        if (items.size() == 1) {
            return Optional.of(items.get(0));
        } else if (items.size() > 1) {
            throw new IllegalArgumentException("More than one record found");
        } else {
            return Optional.empty();
        }
    }

    protected abstract String getTableName();


    protected PreparedStatement createStatement(String query, Object... params) throws SQLException {
        PreparedStatement statement = connection.prepareStatement(query);
        for (int i = 1; i <= params.length; i++) {
            statement.setObject(i, params[i - 1]);
        }

        return statement;
    }
}
