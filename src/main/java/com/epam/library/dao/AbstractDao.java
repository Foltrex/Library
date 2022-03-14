package com.epam.library.dao;

import com.epam.library.entity.Identifable;
import com.epam.library.exception.DaoException;
import com.epam.library.mapper.RowMapper;
import com.epam.library.service.EntityService;

import java.sql.*;
import java.util.*;

public abstract class AbstractDao<T extends Identifable> implements Dao<T>, EntityDao {

    private final Connection connection;
    private final RowMapper<T> rowMapper;
    private final String table;

    protected AbstractDao(Connection connection, RowMapper<T> rowMapper, String table) {
        this.connection = connection;
        this.rowMapper = rowMapper;
        this.table = table;
    }

    protected List<T> executeQuery(String query, Object... params) throws DaoException {
        try (PreparedStatement statement = createStatement(query, params)) {
            ResultSet resultSet = statement.executeQuery();
            List<T> entities = new ArrayList<>();
            while (resultSet.next()) {
                T entity = rowMapper.map(resultSet);
                entities.add(entity);
            }

            return entities;
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public Optional<T> getById(long id) throws DaoException {
        String query = String.format("SELECT * FROM %s WHERE id=?;", table);
        return executeForSingleResult(query, id);
    }

    public int calculateNumberOfRows() throws DaoException {
        try {
            Statement statement = connection.createStatement();

            String columnName = "rows_number";
            String query = String.format("SELECT COUNT(id) AS %s FROM %s", columnName, table);
            ResultSet rs = statement.executeQuery(query);

            return rs.next() ? rs.getInt(columnName) : 0;

        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    public List<T> getAll() throws DaoException {
        String query = String.format("SELECT * FROM %s;", table);
        return executeQuery(query);
    }

    public List<T> getFixedNumberOfRecordsFromPosition(int startingPosition, int recordsPerPage) throws DaoException {
        String query = String.format("SELECT * FROM %s LIMIT ?, ?", table);
        return executeQuery(query,startingPosition, recordsPerPage);
    }

    @Override
    public void removeById(Long id) throws DaoException {
        try {
            String query = String.format("DELETE FROM %s WHERE id=?;", table);
            PreparedStatement statement = createStatement(query, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    protected Optional<T> executeForSingleResult(String query, Object... params) throws DaoException {
        List<T> items = executeQuery(query, params);
        if (items.size() == 1) {
            return Optional.of(items.get(0));
        } else if (items.size() > 1) {
            throw new IllegalArgumentException("More than one record found");
        } else {
            return Optional.empty();
        }
    }


    @Override
    public void save(T item) throws DaoException {
        Map<String, Object> fields = extractFields(item);
        String query = (item.getId() == null)
                ? generateInsertQuery(fields.keySet())
                : generateUpdateQuery(fields.keySet(), item.getId());
        executeUpdate(query, fields.values().toArray());
    }

    protected abstract Map<String, Object> extractFields(T item);

    private String generateInsertQuery(Collection<String> fields) {
        return String.format("INSERT INTO %s (%s) VALUES (%s);",
                table,
                String.join(", ", fields),
                String.join(", ", Collections.nCopies(fields.size(), "?"))
                );
    }

    private String generateUpdateQuery(Collection<String> fields, Long id) {
        String updatePrefix = String.format("UPDATE %s SET ", table);
        String updateSuffix = String.format(" WHERE id=%d;", id);
        StringJoiner updateQuery = new StringJoiner(", ", updatePrefix, updateSuffix);
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
        try {
            for (int i = 1; i <= params.length; i++) {
                statement.setObject(i, params[i - 1]);
            }
        } catch (SQLException e) {
            throw new SQLException(e);
        }

        return statement;
    }
}
