package com.ex.utils;

import org.postgresql.Driver;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class PostgresConnectionManager implements ConnectionManager {

    // jdbc:postgresql://host:port/db
    private static final String CONNECTION_STRING_PREFIX = "jdbc:postgresql://";

    //used in the properties to help store and access the values
    //for connecting to the database
    public static final String DB_USERNAME_KEY = "DB_USERNAME";
    public static final String DB_PASSWORD_KEY = "DB_PASSWORD";
    public static final String DB_URL_KEY = "DB_URL";

    private Properties props;

    static {
        try {
            DriverManager.registerDriver(new Driver());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public PostgresConnectionManager() {}
    public PostgresConnectionManager(String username, String password, String url) {
        props = new Properties();
        props.setProperty(DB_USERNAME_KEY, username);
        props.setProperty(DB_PASSWORD_KEY, password);
        props.setProperty(DB_URL_KEY, url);
    }

    public PostgresConnectionManager(Properties props) {
        this.props = props;
    }

    @Override
    public Connection getConnection() throws SQLException {
        return this.getConnection(
                props.getProperty(DB_USERNAME_KEY),
                props.getProperty(DB_PASSWORD_KEY),
                props.getProperty(DB_URL_KEY));
    }

    @Override
    public Connection getConnection(String username, String password, String url) throws SQLException {
        if(!assertUrlFormat(url)) {
            throw new IllegalStateException(String.format("Url is not in the proper format for PostgreSQL database %s", url));
        }
        return DriverManager.getConnection(
                url,
                username,
                password);
    }

    private boolean assertUrlFormat(String url) {
        String sub = url.substring(0, CONNECTION_STRING_PREFIX.length());
        return sub.equals(CONNECTION_STRING_PREFIX);
    }
}
