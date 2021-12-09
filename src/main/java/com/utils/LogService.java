package com.utils;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Calendar;
import java.util.logging.*;
/**
 * @author mayanksareen
 */

public class LogService {
    FileWriter logWriter;
    public LogService() {
        try {
            createNewFile(Constants.LOGGER_FILE_NAME);
            logWriter = new FileWriter(Constants.LOGGER_FILE_NAME, true);
        } catch (IOException e) {}
    }

    public LogService(String path) {
        try {
            createNewFile(path);
            logWriter = new FileWriter(path, true);
        } catch (IOException ex) {}
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
        } catch (IOException e) {}
    }

    private static void createNewFile(String path) {
        File logFile = new File(path);
        if (!logFile.exists()) {
            try {
                logFile.createNewFile();
            } catch (IOException e) {}
        }
    }

    private  String getCurrentDateAndTime() {
        return String.valueOf(Calendar.getInstance().getTime());
    }
}
