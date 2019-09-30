package com.ex.utils;

import java.sql.Connection;
import java.sql.SQLException;

public interface ConnectionManager {
    Connection getConnection() throws SQLException;
    Connection getConnection (String username, String password, String url) throws SQLException;
}
