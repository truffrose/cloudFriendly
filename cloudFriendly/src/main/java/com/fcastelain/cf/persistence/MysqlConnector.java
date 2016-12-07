package main.java.com.fcastelain.cf.persistence;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;
import java.util.Properties;

/**
 * Created by fcastelain on 28/11/16.
 */
public enum MysqlConnector {

    INSTANCE;

    private static final String PROPERTIES_FILE = "resources/mysql.properties";

    private static String url;
    private static String username;
    private static String password;
    private Connection conn;

    // automatically load with the class
    static {
        Properties properties = new Properties();
        try {
            properties.load(new FileInputStream(new File(PROPERTIES_FILE)));
        } catch (IOException e) {
            e.printStackTrace();
        }

        url = properties.getProperty("URL") + properties.getProperty("DB_NAME") + properties.getProperty("PARAMS");
        username = properties.getProperty("USERNAME");
        password = properties.getProperty("PASSWORD");
    }

    public Connection getConnection() {
        try {
            conn = DriverManager.getConnection(url, username, password);
        } catch (SQLException e) {
            try {
                conn.rollback();
                conn.close();
            } catch (SQLException e1) {
                throw new ConnectionException("Rollback and close connection exception." + e1);
            }
            throw new ConnectionException("Cannot get connection using url:" + url + " | " + e);
        }
        return conn;
    }

}
