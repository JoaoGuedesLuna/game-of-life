package dev.guedes.gameoflife.utils.cli;

import java.io.IOException;
import java.util.Locale;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Utility class for manipulating the terminal/console, providing methods
 * to clear the console screen.
 *
 * @author João Guedes
 */
public final class Terminal {
    private static final Logger LOGGER = Logger.getLogger(Terminal.class.getName());
    private static final String ANSI_CLEAR = "\033[H\033[2J";

    private Terminal() {
        throw new UnsupportedOperationException("ConsoleUtils is a utility class and cannot be instantiated.");
    }

    public static void clear() {
        if (!clearUsingSystemCommand()) clearUsingAnsi();
    }

    public static void pause(Scanner scanner, String message) {
        System.out.print(message);
        scanner.nextLine();
    }

    private static boolean clearUsingSystemCommand() {
        String osName = System.getProperty("os.name", "")
                .toLowerCase(Locale.ROOT);

        ProcessBuilder builder = osName.contains("windows")
                ? new ProcessBuilder("cmd", "/c", "cls")
                : new ProcessBuilder("clear");

        try {
            Process process = builder.inheritIO().start();
            return process.waitFor() == 0;
        }
        catch (IOException | InterruptedException e) {
            Thread.currentThread().interrupt();
            LOGGER.log(Level.FINE, "Could not clear console using system command. ", e);
            return false;
        }
    }

    private static void clearUsingAnsi() {
        System.out.print(ANSI_CLEAR);
        System.out.flush();
    }
}
