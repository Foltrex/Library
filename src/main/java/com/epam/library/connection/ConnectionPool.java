package com.epam.library.data.connect;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ConnectionPool {
    private static ConnectionPool instance;

    private static final Lock LOCK = new ReentrantLock();

    private ConnectionPool() {
    }

    public static ConnectionPool getInstance(){
        ConnectionPool localInstance = instance;
        if (localInstance == null) {
            LOCK.lock();
            localInstance = instance;
            try {
                if (localInstance == null) {
                    localInstance = new ConnectionPool();
                    instance = localInstance;
                }
            } finally {
                LOCK.unlock();
            }
        }

        return localInstance;
    }

    public Connection getConnection() throws NamingException, SQLException {
        Context context = new InitialContext();
        DataSource dataSource = (DataSource) context.lookup("java:comp/env/jdbc/library");
        Connection connection = dataSource.getConnection();

        return connection;
    }
}
