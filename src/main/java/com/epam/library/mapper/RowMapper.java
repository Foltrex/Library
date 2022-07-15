package com.epam.library.mapper;

import com.epam.library.entity.*;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Maps result set to passed entity
 *
 * @param <T>  the entity class
 */
public interface RowMapper<T extends Identifable> {

    T map(ResultSet resultSet) throws SQLException;
}
