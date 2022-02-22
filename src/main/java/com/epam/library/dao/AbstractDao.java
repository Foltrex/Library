package com.epam.library.dao;

import com.epam.library.entity.Entity;
import com.epam.library.entity.Identifable;
import com.epam.library.exception.DaoException;
import com.epam.library.mapper.RowMapper;

import java.sql.*;
import java.util.*;

public abstract class AbstractDao<T extends Identifable> implements Dao<T> {

    public static final String ID = "id";

    private final Connection connection;
    private final RowMapper<T> rowMapper;

    private final String table;

    protected AbstractDao(Connection connection, RowMapper<T> rowMapper, String table) {
        this.connection = connection;
        this.rowMapper = rowMapper;
        this.table = table;
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
        RowMapper<T> mapper = (RowMapper<T>) RowMapper.create(table);
        String query = "SELECT * FROM " + table + " WHERE id=?;";
        return executeForSingleResult(query, mapper, id);
    }

    public List<T> getAll() throws DaoException {
        RowMapper<T> mapper = (RowMapper<T>) RowMapper.create(table);
        return executeQuery("SELECT * FROM " + table + ";", mapper);
    }

    @Override
    public void removeById(Long id) throws SQLException {
        PreparedStatement statement = createStatement("DELETE FROM " + table + " WHERE id=?;", id);
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

    @Override
    public void save(T item) throws SQLException, DaoException {
        Map<String, Object> fields = extractFields(item);
        String query = (item.getId() == null) ? generateInsertQuery(fields.keySet()) : generateUpdateQuery(fields.keySet());
        executeUpdate(query, fields.values());
    }

    protected abstract Map<String, Object> extractFields(T item);

    private String generateInsertQuery(Collection<String> fields) {
        return String.format("INSERT INTO %s(%s) VALUES (%s);",
                table,
                String.join(", ", fields),
                String.join(", ", Collections.nCopies(fields.size(), "?"))
                );
    }

    private String generateUpdateQuery(Collection<String> fields) {
        String updatePrefix = "UPDATE " + table;
        StringJoiner updateQuery = new StringJoiner(", ", updatePrefix, ";");
        for (String field: fields) {
            updateQuery.add(field + " = ?");
        }

        return updateQuery.toString();
    }

    protected void executeUpdate(String query, Object... params) throws DaoException {
        try (PreparedStatement statement = createStatement(query, params)) {
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    private PreparedStatement createStatement(String query, Object... params) throws SQLException {
        PreparedStatement statement = connection.prepareStatement(query);
        for (int i = 1; i <= params.length; i++) {
            statement.setObject(i, params[i - 1]);
        }

        return statement;
    }
}
