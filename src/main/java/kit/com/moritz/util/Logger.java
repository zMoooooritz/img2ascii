package kit.com.moritz.util;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.text.SimpleDateFormat;
import java.util.Date;


/**
 * Simple Logger Class to debug output to the commandline and the log file
 */
public class Logger {

    private static final LevelOfDetail MODE = LevelOfDetail.NONE;

    private static final boolean STDOUT = true;

    private static void print(String message, @NotNull LevelOfDetail level) {
        String log = "[ " + new SimpleDateFormat("HH:mm:ss dd.MM.yyyy").format(new Date()) +
                " " + System.getProperty("user.name") +  "@" + getLocalHost() + " ]" +
                " {LOG LEVEL} " + level.toString() + " {CLASS} " +
                new Exception().getStackTrace()[2].getClassName() + " {METHOD} " +
                new Exception().getStackTrace()[2].getMethodName() + "() {MESSAGE} " + message;

        if (STDOUT) {
            System.out.println(log);
        }

        FileAccess.appendText(Constants.LOG_FILE_NAME, log);
    }

    /**
     * Prints a INFO-Message if allowed
     *
     * @param message The message to print
     */
    public static void info(String message) {
        if (MODE.getLevel() <= 0) {
            print(message, LevelOfDetail.INFO);
        }
    }

    /**
     * Prints a DEBUG-Message if allowed
     *
     * @param message The message to print
     */
    public static void debug(String message) {
        if (MODE.getLevel() <= 0) {
            print(message, LevelOfDetail.DEBUG);
        }
    }

    /**
     * Prints a WARN-Message if allowed
     *
     * @param message The message to print
     */
    public static void warn(String message) {
        if (MODE.getLevel() <= 0) {
            print(message, LevelOfDetail.WARN);
        }
    }

    /**
     * Prints a ERROR-Message if allowed
     *
     * @param message The message to print
     */
    public static void error(String message) {
        if (MODE.getLevel() <= 0) {
            print(message, LevelOfDetail.ERROR);
        }
    }

    /**
     * Prints a FATAL-Message if allowed
     *
     * @param message The message to print
     */
    public static void fatal(String message) {
        if (MODE.getLevel() <= 0) {
            print(message, LevelOfDetail.FATAL);
        }
    }

    /**
     * Define Level Of Detail with fixed numbers
     */
    private enum LevelOfDetail {
        INFO(0), DEBUG(1), WARN(2), ERROR(3), FATAL(4), NONE(5);

        int level;

        @Contract(pure = true)
        LevelOfDetail(int level) {
            this.level = level;
        }

        @Contract(pure = true)
        int getLevel() {
            return level;
        }
    }

    /**
     * Tries to collect information about the LocalHostName
     *
     * @return The LocalHostName
     */
    private static String getLocalHost() {
        try {
            return InetAddress.getLocalHost().getHostName();
        } catch (UnknownHostException e) {
            e.printStackTrace();
            return "PC";
        }
    }

}
