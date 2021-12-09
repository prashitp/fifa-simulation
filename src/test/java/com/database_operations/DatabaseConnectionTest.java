package com.database_operations;

import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class DatabaseConnectionTest {
    public static Connection connection;

    @Test
    public void setConnectionWithDatabaseTest() throws SQLException {
        connection = DatabaseConnection.getInstance().getConnection();
        assertTrue(connection != null && !connection.isClosed(), "Connection set to Database");
    }
}

