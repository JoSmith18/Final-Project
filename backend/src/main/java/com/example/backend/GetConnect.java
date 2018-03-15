package com.example.backend;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class GetConnect {
    public static Connection get() throws SQLException {
        return DriverManager.getConnection("jdbc:postgresql:datingDB", "basecamp", "pgpass");
    }
}
