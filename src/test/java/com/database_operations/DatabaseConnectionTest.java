package com.database_operations;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;
import java.sql.Connection;
import java.sql.SQLException;

public class DatabaseConnectionTest {
    public static Connection connection;

    @Test
    public void setConnectionWithDatabaseTest() throws SQLException {
        connection = DatabaseConnection.getInstance().getConnection();
        assertTrue(connection != null && !connection.isClosed(), "Connection set to Database");
    }
}

