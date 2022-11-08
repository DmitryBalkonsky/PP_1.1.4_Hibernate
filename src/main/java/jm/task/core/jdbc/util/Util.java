package jm.task.core.jdbc.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Util {
    private static Connection connection = null;
    private static final String MY_URL = "jdbc:mysql://localhost:3306/mydbtest";
    private static final String MY_PASSWORD = "494144598Dima)";
    private static final String MY_USERNAME = "root";

    public static Connection getConnection() {
        try {
            return DriverManager.getConnection(MY_URL, MY_USERNAME, MY_PASSWORD);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
