package com;
/**
 * @author Mayank Sareen
 */
import org.junit.jupiter.api.*;
import java.io.*;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.logging.Level;
import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class LogServiceTest {
    public static LogService logService = null;
    public static String temporaryFile = "src/main/java/logger.log";

    @BeforeAll
    public static void init() {
        logService = new LogService(temporaryFile);
    }

    @AfterAll
    public static void deleteTemporaryFile() {
        File file = new File(String.valueOf(temporaryFile));
        file.delete();
    }

    @Test
    @Order(1)
    public void checkLoggerFileExistsTest() {
        File file = new File(String.valueOf(temporaryFile));
        assertTrue(file.exists());
    }

    @Test
    @Order(2)
    public void getCurrentDateAndTimeTest() {
        logService.log(Level.SEVERE, "My test class!");
        Calendar cal = Calendar.getInstance();
        assertTrue(readFile().contains(String.valueOf(cal.getTime())));
    }

    @Test
    @Order(3)
    public void loggerFileContentTest() {
        logService.log(Level.SEVERE, "My test class!");
        assertTrue(readFile().contains("SEVERE : My test class!"));
    }

    private List<String> readFile() {
        try {
            List<String> lines = new ArrayList<String>();
            BufferedReader br = new BufferedReader(new FileReader(String.valueOf(temporaryFile)));
            String line = br.readLine();
            while (line != null) {
                lines.add(line);
                line = br.readLine();
            }
            br.close();
            return lines;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
