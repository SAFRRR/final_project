package com.safronova.webproject.model.pool;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.atomic.AtomicBoolean;


public class ConnectionPool {
    private static final Logger logger = LogManager.getLogger();
    private static final int DEFAULT_POOL_SIZE = 8;
    private static ConnectionPool instance;
    private static AtomicBoolean isPoolCreated = new AtomicBoolean(false);
    private BlockingQueue<ProxyConnection> freeConnections;
    private BlockingQueue<ProxyConnection> usedConnections;

    private ConnectionPool(){
        freeConnections = new LinkedBlockingDeque<>(DEFAULT_POOL_SIZE);
        usedConnections = new LinkedBlockingDeque<>(DEFAULT_POOL_SIZE);
        for (int i = 0; i < DEFAULT_POOL_SIZE; i++) {
            try {
                Connection connection = ConnectionFactory.createConnection();
                ProxyConnection proxyConnection = new ProxyConnection(connection);
                freeConnections.offer(proxyConnection);
            } catch (SQLException e) {
                logger.error("Caught an error trying to create connection: ", e);
            }
        }
        if (freeConnections.isEmpty()) {
            logger.fatal("Cannot create connections, pool is empty.");
            throw new RuntimeException("Cannot create connections, pool is empty.");
        }
    }

    public static ConnectionPool getInstance(){
        while(instance == null){
            if(isPoolCreated.compareAndSet(false, true)){
                instance = new ConnectionPool();
            }
        }
        return instance;
    }

    public Connection getConnection(){
        ProxyConnection proxyConnection = null;
        try{
            proxyConnection = freeConnections.take();
            usedConnections.put(proxyConnection);
        } catch (InterruptedException e) {
            logger.error("Process was interrupted", e);
            Thread.currentThread().interrupt();
        }
        return proxyConnection;
    }

    public void releaseConnection(Connection connection){
        if (!(connection instanceof ProxyConnection)){
            logger.error(connection + " doesn't belong to the pool, illegal connection type");
            throw new RuntimeException( connection + " doesn't belong to the pool, illegal connection type");
        }
        usedConnections.remove(connection);
        try {
            freeConnections.put((ProxyConnection) connection);
        } catch (InterruptedException e) {
            logger.warn("Process was interrupted", e);
            Thread.currentThread().interrupt();
        }
    }

    public void destroyPool(){
        for (int i = 0; i < DEFAULT_POOL_SIZE; i++){
            try{
                freeConnections.take().reallyClose();
            } catch (InterruptedException e) {
                logger.warn("Process was interrupted", e);
                Thread.currentThread().interrupt();
            } catch (SQLException e) {
                logger.error("Unable to close connection in a proper way", e);
            }
        }
        deregisterDrivers();
    }

    private void deregisterDrivers(){
        DriverManager.getDrivers().asIterator().forEachRemaining(driver -> {
            try{
                DriverManager.deregisterDriver(driver);
            } catch (SQLException e) {
                logger.error( "Failed to deregister driver: " + driver, e);
            }
        });
    }
}
