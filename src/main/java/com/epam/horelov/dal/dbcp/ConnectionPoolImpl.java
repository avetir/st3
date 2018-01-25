package com.epam.horelov.dal.dbcp;

import com.epam.horelov.exception.CustomException;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import static java.lang.Thread.sleep;

public class ConnectionPoolImpl implements ConnectionPool{

    private static final String CREATE_CONNECTION_ERROR_MSG = "Cannot create connection to the database";
    private static final String DB_URL = "jdbc:postgresql://localhost:5432/st3";
    private static final String DB_USER = "postgres";
    private static final String DB_PASSWORD = "admin";

    private Map<Connection, Boolean> connections;

    public ConnectionPoolImpl(){
        connections = new HashMap<>();
    }

    private Connection createConnection() {
        String path = new File("src/main/resources/dbconfig.properties").getAbsolutePath();
        Properties props = new Properties();
        try(FileInputStream in = new FileInputStream(path)) {
            props.load(in);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            return DriverManager.getConnection(props.getProperty("dbUrl"), props.getProperty("dbUserName"), props.getProperty("dbPassword"));
        } catch (SQLException ex) {
            throw new CustomException(CREATE_CONNECTION_ERROR_MSG, ex);
        }
    }

    @Override
    public Connection getConnection() {
        if (connections.isEmpty()) {
            Connection newConnection = createConnection();
            connections.put(newConnection, true);
            return newConnection;
        } else {
            if (connections.size() < 10) {
                for (Map.Entry<Connection, Boolean> connection : connections.entrySet()) {
                    if (Boolean.FALSE.equals(connection.getValue())){
                        connection.setValue(true);
                        return connection.getKey();
                    }
                }
                Connection newConnection = createConnection();
                connections.put(newConnection, true);
                return newConnection;
            } else {
                while (true){
                    for (Map.Entry<Connection, Boolean> connection : connections.entrySet()) {
                        if (Boolean.FALSE.equals(connection.getValue())){
                            connection.setValue(true);
                            return connection.getKey();
                        }
                    }
                    try {
                        sleep(100);
                    } catch (InterruptedException ex) {

                    }
                }
            }
        }
    }

    @Override
    public void returnConnection(Connection connection) {
        connections.replace(connection, false);
    }
}
