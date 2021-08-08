package com.safronova.webproject.model.pool;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

class ConnectionFactory {
    private static final Logger logger = LogManager.getLogger();
    private static final Properties properties = new Properties();
    private static final String DB_PROPERTIES_NAME = "db.properties";
    private static final String DB_DRIVER_NAME_PROPERTY = "driver";
    private static final String DB_URL_PROPERTY = "url";
    private static final String DB_URL;

    static{
        String driverName = null;
        try (InputStream inputStream = ConnectionFactory.class.getClassLoader().getResourceAsStream(DB_PROPERTIES_NAME)){
            properties.load(inputStream);
            driverName = properties.getProperty(DB_DRIVER_NAME_PROPERTY);
            Class.forName(driverName);
            DB_URL = properties.getProperty(DB_URL_PROPERTY);
        } catch (IOException e) {
            logger.fatal("Unable to read database properties", e);
            throw new RuntimeException("Unable to read database propertie", e);
        }catch (ClassNotFoundException e){
            logger.fatal("Unable to register database driver: " + driverName, e);
            throw new RuntimeException("Unable to register database driver: " + driverName, e);
        }
    }

    private ConnectionFactory() {}

    static Connection createConnection() throws SQLException {
        return DriverManager.getConnection(DB_URL, properties);
    }
}
