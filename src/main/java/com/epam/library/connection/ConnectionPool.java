package com.epam.library.connection;

import com.epam.library.exception.DaoException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayDeque;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ConnectionPool {
    private static final Logger LOGGER = LogManager.getLogger(ConnectionPool.class);

    private static ConnectionPool instance;

    private static final Lock LOCK = new ReentrantLock();

    private static final int INITIAL_PROXY_POOL_SIZE = 10;

    private final Lock connectionsLock = new ReentrantLock();
    private final Semaphore semaphore = new Semaphore(INITIAL_PROXY_POOL_SIZE, true);

    private final Queue<ProxyConnection> availableConnections = new ArrayDeque<>(INITIAL_PROXY_POOL_SIZE);
    private final Queue<ProxyConnection> connectionsInUse = new ArrayDeque<>(INITIAL_PROXY_POOL_SIZE);

    private final ConnectionFactory connectionFactory = new ConnectionFactory();

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
                    List<ProxyConnection> newProxyConnections =
                            instance.connectionFactory.create(instance, INITIAL_PROXY_POOL_SIZE);
                    instance.availableConnections.addAll(newProxyConnections);
                }
            } catch (DaoException e) {
                LOGGER.error(e.getMessage(), e);
            } finally {
                LOCK.unlock();
            }
        }

        return localInstance;
    }

    public void returnConnection(ProxyConnection proxyConnection) {
        connectionsLock.lock();
        try {
            if (connectionsInUse.contains(proxyConnection)) {
                availableConnections.offer(proxyConnection);
                connectionsInUse.remove(proxyConnection);
                semaphore.release();
            }
        } finally {
            connectionsLock.unlock();
        }
    }

    public ProxyConnection getConnection() {
        connectionsLock.lock();
        ProxyConnection proxyConnection = null;
        try {
            // if the pool is empty, then wait for at least one connection to become free
            if (semaphore.tryAcquire(1, TimeUnit.MINUTES)) {
                ProxyConnection connection = availableConnections.remove();
                connectionsInUse.offer(connection);
                return connection;
            } else {
                LOGGER.warn("Time limit exceeded");
            }

        } catch (InterruptedException e) {
            LOGGER.error("Сonnection getting error", e);
        } finally {
            connectionsLock.unlock();
        }

        return proxyConnection;
    }
}
