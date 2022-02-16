package com.epam.library.connection;

import java.util.ArrayDeque;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ConnectionPool {
    private static ConnectionPool instance;

    private static final Lock LOCK = new ReentrantLock();

    private final Lock connectionsLock = new ReentrantLock();

    private static final int INITIAL_POOL_SIZE_OF_PROXY_CONNECTIONS = 10;
    private static final int NUMBER_OF_ADDED_PROXY_CONNECTIONS = 5;
    private final Queue<ProxyConnection> availableConnections = new ArrayDeque<>(INITIAL_POOL_SIZE_OF_PROXY_CONNECTIONS);
    private final Queue<ProxyConnection> connectionsInUse = new ArrayDeque<>(INITIAL_POOL_SIZE_OF_PROXY_CONNECTIONS);

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
                }
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
            }
        } finally {
            connectionsLock.unlock();
        }
    }

    public ProxyConnection getConnection() {
        connectionsLock.lock();
        ProxyConnection proxyConnection;
        try {
            if (availableConnections.size() == 0) {
                List<ProxyConnection> newProxyConnections =
                        connectionFactory.create(instance, NUMBER_OF_ADDED_PROXY_CONNECTIONS);
                availableConnections.addAll(newProxyConnections);
            }

            proxyConnection =  availableConnections.poll();
            connectionsInUse.add(proxyConnection);
        } finally {
            connectionsLock.unlock();
        }

        return proxyConnection;
    }
}
