package com.epam.library.connection;

import com.epam.library.exception.DaoException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ConnectionFactory {

    private static final String CONTEXT_PATH = "java:comp/env/jdbc/library";

    public ProxyConnection create(ConnectionPool pool) {
        List<ProxyConnection> proxyConnections = create(pool, 1);
        return proxyConnections.get(0);
    }

    public List<ProxyConnection> create(ConnectionPool pool, int count) {
        List<ProxyConnection> proxyConnections = new ArrayList<>(count);
        try {
            for (int i = 0; i < count; ++i) {
                proxyConnections.add(new ProxyConnection(createConnection(CONTEXT_PATH), pool));
            }
        } catch (NamingException | SQLException e) {
            throw new RuntimeException("Exception while connecting to SQL Server", e);
        }

        return proxyConnections;
    }

    private Connection createConnection(String contextPath) throws NamingException, SQLException {
        Context context = new InitialContext();
        DataSource dataSource = (DataSource) context.lookup(contextPath);
        Connection connection = dataSource.getConnection();

        return connection;
    }
}
