package com.database_operations;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import java.sql.Connection;

public class DatabaseConnectionTest {
    @Test
    public void setConnectionWithDatabaseTest() {
        Connection connection = DatabaseConnection.setConnectionWithDatabase();
        try {
            assertEquals(true, !connection.isClosed(), "Connection set to Database");
        } catch (Exception ex) {
            fail("Exception occurred"+ ex.getMessage());
        }
    }
}

