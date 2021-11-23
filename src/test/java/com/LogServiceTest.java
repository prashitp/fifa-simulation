package com;
/**
 * @author Mayank Sareen
 */
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.logging.FileHandler;

public class LogServiceTest {
    @Test
    public void checkLoggerFileExistsTest() {
        FileHandler fileHandler = new LogService().initializeLogger();
        assertTrue(String.valueOf(fileHandler.getFormatter()).contains("SimpleFormatter"));
    }
}
