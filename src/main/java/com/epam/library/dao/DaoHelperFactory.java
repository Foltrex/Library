package com.epam.library.dao;

import com.epam.library.connection.ConnectionPool;

public class DaoHelperFactory {

    public DaoHelper create() {
        return new DaoHelper(ConnectionPool.getInstance());
    }
}
