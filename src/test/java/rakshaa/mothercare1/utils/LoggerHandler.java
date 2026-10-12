package rakshaa.mothercare1.utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class LoggerHandler {

    private static final Logger logger = LogManager.getLogger("rakshaa.mothercare1");

    private LoggerHandler() {}

    public static void debug(String message) { logger.debug(message); }
    public static void info(String message)  { logger.info(message); }
    public static void warn(String message)  { logger.warn(message); }
    public static void error(String message) { logger.error(message); }

    public static void error(String message, Throwable t) { logger.error(message, t); }
    public static void fatal(String message)              { logger.fatal(message); }
    public static void fatal(String message, Throwable t) { logger.fatal(message, t); }

    public static void testStart(String testCaseId, String testCaseName) {
        info("==========================================================");
        info("TEST START  [" + testCaseId + "] " + testCaseName);
        info("==========================================================");
    }

    public static void testEnd(String testCaseId, String result) {
        info("TEST END    [" + testCaseId + "] Result: " + result);
        info("----------------------------------------------------------");
    }
}
