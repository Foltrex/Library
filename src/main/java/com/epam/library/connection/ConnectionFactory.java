package com.epam.library.connection;

import com.epam.library.exception.DaoException;
import com.epam.library.exception.PageCommandException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;

public class ConnectionFactory {

    private static final String DATABASE_PROPERTIES = "/database.properties";

    private static final String SQL_DRIVER = "com.mysql.cj.jdbc.Driver";

    public ProxyConnection create(ConnectionPool pool) throws DaoException {
        List<ProxyConnection> proxyConnections = create(pool, 1);
        return proxyConnections.get(0);
    }

    public List<ProxyConnection> create(ConnectionPool pool, int count) throws DaoException {
        List<ProxyConnection> proxyConnections = new ArrayList<>(count);
        try {
            for (int i = 0; i < count; ++i) {
                proxyConnections.add(new ProxyConnection(createConnection(SQL_DRIVER), pool));
            }
        } catch (IOException | ClassNotFoundException | SQLException e) {
            throw new DaoException("Exception while connecting to SQL Server", e);
        }

        return proxyConnections;
    }

    private Connection createConnection(String driver) throws ClassNotFoundException, IOException, SQLException {
        // ?autoReconnect=true
        Class.forName(driver);

        Properties props = new Properties();
        try(InputStream inputStream
                    = ConnectionFactory.class.getClassLoader().getResourceAsStream(DATABASE_PROPERTIES)) {
            props.load(inputStream);
        }

        String url = props.getProperty("url");
        String username = props.getProperty("username");
        String password = props.getProperty("password");

        return DriverManager.getConnection(url, username, password);
    }
}
