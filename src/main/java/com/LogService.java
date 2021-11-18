package com;

import java.util.logging.*;
/**
 * @author Mayank Sareen
 */
public class LogService {
    private static final Logger logs = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
    public void log(Level logType, String logMessage) {
        logs.log(logType, logMessage);
    }

    public void initializeLogger() {
        FileHandler fileHandler;
        try {
            fileHandler = new FileHandler(Constants.LOGGER_FILE_PATH, true);
            logs.addHandler(fileHandler);
            SimpleFormatter simpleFormatter = new SimpleFormatter();
            fileHandler.setFormatter(simpleFormatter);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
