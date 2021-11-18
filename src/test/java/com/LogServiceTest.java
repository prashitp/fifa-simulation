package com;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.io.File;
import java.util.logging.FileHandler;

public class LogServiceTest {
    @Test
    public void checkLoggerFileExistsTest() {
        FileHandler fileHandler = new LogService().initializeLogger();
        assertTrue(String.valueOf(fileHandler.getFormatter()).contains("SimpleFormatter"));
    }
}
