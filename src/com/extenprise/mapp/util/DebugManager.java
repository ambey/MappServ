package com.extenprise.mapp.util;


import org.apache.log4j.Logger;


/**
 * DebugManager is a wrapper class used for debugging. It makes use of the log4j library.
 *
 
 */
public class DebugManager {
    /**
     * logger to be used for debugging
     */
    private Logger logger = null;
    private static DebugManager debugLog = DebugManager.getInstance("com.extenprise.mapp.debug");
    private static DebugManager errorLog = DebugManager.getInstance("com.extenprise.mapp.error");
    private static DebugManager auditLog = DebugManager.getInstance("com.extenprise.audit");

    /**
     * Construct a new debug manager for logging using the given class.
     * @param c the class
     */

    /**
     * Construct a new debug manager for logging using the given string.
     * @param name the string representation of the logger (usually a fully qualified class name).
     */
    private DebugManager(String name) {
        logger = Logger.getLogger(name);
    }

    /**
     * Get a debug manager for the given class name
     * @param name the class name
     * @return the debug manager
     */
    public static DebugManager getInstance(String name) {
        return new DebugManager(name);
    }

    /**
     * Get the created logger.
     * @return the logger.
     */
    public Logger getLogger() {
        return logger;
    }

    /**
     * Generate a debug-level message.
     * @param message the message to print
     */
    public void debug(String message) {
        logger.debug(message);
    }

    /**
     * Generate a debug-level message.
     * @param message the message to print
     */
    public void debug(Object message) {
        logger.debug(message);
    }

    /**
     * Generate a debug-level message.
     * @param message the message to print
     */
    public void debug(int message) {
        logger.debug(""+message);
    }

    /**
     * Generate a debug-level message.
     * @param message the message to print
     * @param exception the Exception to print
     */
    public void debug(String message,Throwable exception) {
        logger.debug(message,exception);
    }

    /**
     * Generate a info-level message.
     * @param message the string to print
     */
    public void info(String message) {
        logger.info(message);
    }

    /**
     * Generate a info-level message.
     * @param message the message to print
     * @param exception the Exception to print
     */
    public void info(String message,Throwable  exception) {
        logger.info(message,exception);
    }

    /**
     * Generate a warn-level message.
     * @param message the message to print
     */
    public void warn(String message) {
        logger.warn(message);
    }

    /**
     * Generate a warn-level message.
     * @param message the message to print
     * @param exception the Exception to print
     */
    public void warn(String message,Throwable  exception) {
        logger.warn(message,exception);
    }

    /**
     * Generate a error-level message.
     * @param message the message to print
     */
    public void error(String message) {
        logger.error(message);
    }

    /**
     * Generate a error-level message.
     * @param exception the Exception to print
     */
    public void error(Object exception) {
        logger.error(exception);
    }

    /**
     * Generate a error-level message.
     * @param message the message to print
     * @param exception the Exception to print
     */
    public void error(String message,Throwable  exception) {
        logger.error(message,exception);
    }

    /**
     * Generate a fatal-level message.
     * @param message the message to print
     */
    public void fatal(String message) {
        logger.fatal(message);
    }

    /**
     * Generate a fatal-level message.
     * @param message the message to print
     * @param exception the exception
     */
    public void fatal(String message,Throwable  exception) {
        logger.fatal(message,exception);
    }


    /**
     *  Error log Tracing
     * @param className - name of class
     * @param e - exception name
     */
    public synchronized static void doLog(String className, Exception e)    {
        errorLog.error("["+className+"] "+e);
        StackTraceElement stele[]=e.getStackTrace();
        for(int i=0;i<stele.length;i++)
            errorLog.error("["+className+"] "+stele[i]);
    }

    public synchronized static void doError(String className, String message,Throwable  exception) {
        errorLog.error("["+className+"] "+exception);
        StackTraceElement stele[]=exception.getStackTrace();
        for(int i=0;i<stele.length;i++)
            errorLog.error("["+className+"] "+stele[i]);
    }

    public synchronized static void doError(String className, String message) {
        errorLog.error("["+className+"] "+ message);
    }

    public synchronized static void doError(String className, Object exception) {
        errorLog.error("["+className+"] "+ exception);
    }

    /**
     * Debug methods
     * @param className - string for which class debugging
     * @param message - error message
     * @param exception - kind of exception come.
     */
    public synchronized static void doDebug (String className, String message,Throwable  exception) {
        debugLog.debug ("["+className+"] "+ message, exception);
    }

    public synchronized static void doDebug (String className, String message) {
        debugLog.debug ("["+className+"] "+ message);
    }

    public synchronized static void doDebug (String className, Object exception) {
        debugLog.debug ("["+className+"] "+ exception);
    }

    /**
     *  Audit Information details for auditing purpose.
     * @param className - for which user you are writing info.
     * @param message - audit info message.
     */

    public synchronized static void doAudit (String className, String message) {
        auditLog.info ("["+ className +"] "+ message);
    }

    public synchronized static void doAudit (String message) {
        auditLog.info (" " + message);
    }


    
}
