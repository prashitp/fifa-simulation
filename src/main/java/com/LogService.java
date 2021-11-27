package com;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.logging.*;
/**
 * @author Mayank Sareen
 */

public class LogService {
    FileWriter logWriter;
    public LogService() {
        try {
            createNewFile(Constants.LOGGER_FILE_PATH);
            logWriter = new FileWriter(Constants.LOGGER_FILE_PATH, true);
        } catch (IOException e) {
            System.out.println("Failed to create log Writer" + e.getStackTrace());
        }
    }

    public LogService(String path) {
        try {
            createNewFile(path);
            logWriter = new FileWriter(path, true);
        } catch (IOException ex) {
            System.out.println("Failed to create log Writer" + ex.getMessage());
        }
    }
   public void log(Level logType, String logMessage) {
        writeToFile(logType, logMessage);
    }

    private void writeToFile(Level logType, String logMessage) {
        try {
            logWriter.write("================================================================================================");
            logWriter.write("\n");
            logWriter.write(getCurrentDateAndTime());
            logWriter.write("\n");
            logWriter.write("================================================================================================");
            logWriter.write("\n");
            logWriter.write(logType.toString() + " : " + logMessage);
            logWriter.write("\n");
            logWriter.close();
        } catch (IOException e) {
//            System.out.println("An error occurred." + e.getStackTrace());
        }
    }

    private static void createNewFile(String path) {
        File logFile = new File(path);
        if (!logFile.exists()) {
            try {
                logFile.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private  String getCurrentDateAndTime() {
        return String.valueOf(Calendar.getInstance().getTime());
    }
}
