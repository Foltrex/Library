package com.epam.library.dao;

import com.epam.library.connection.ConnectionPool;

/** Creates dao helper object */
public class DaoHelperFactory {

    public DaoHelper create() {
        return new DaoHelper(ConnectionPool.getInstance());
    }
}
