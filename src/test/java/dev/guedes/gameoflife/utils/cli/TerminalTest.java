package dev.guedes.gameoflife.utils.cli;

import org.junit.jupiter.api.Test;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Unit tests for the {@link Terminal} utility class.
 *
 * @author João Guedes
 */
class TerminalTest {
    @Test
    void constructor_ShouldThrowUnsupportedOperationException() throws Exception {
        Constructor<Terminal> constructor = Terminal.class.getDeclaredConstructor();
        constructor.setAccessible(true);

        InvocationTargetException ex = assertThrows(InvocationTargetException.class, constructor::newInstance);
        assertInstanceOf(UnsupportedOperationException.class, ex.getCause());
    }

    @Test
    void clear_ShouldNotThrowException() {
        assertDoesNotThrow(Terminal::clear);
    }

    @Test
    void clear_ShouldUseAnsi_WhenSystemCommandFails() {
        String originalOs = System.getProperty("os.name");
        PrintStream originalOut = System.out;

        try {
            System.setProperty("os.name", "invalid-os");

            ByteArrayOutputStream outContent = new ByteArrayOutputStream();
            System.setOut(new PrintStream(outContent));

            Terminal.clear();

            String output = outContent.toString();
            assertTrue(output.contains("\033[H\033[2J"));
        }
        finally {
            System.setProperty("os.name", originalOs);
            System.setOut(originalOut);
        }
    }

    @Test
    void pause_ShouldDisplayCustomMessage() {
        PrintStream originalOut = System.out;
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();

        String message = "Waiting for user...";
        String input = System.lineSeparator();
        Scanner scanner = new Scanner(new ByteArrayInputStream(input.getBytes()));

        try {
            System.setOut(new PrintStream(outContent));

            assertDoesNotThrow(() -> Terminal.pause(scanner, message));
            assertTrue(outContent.toString().contains(message));
        } finally {
            System.setOut(originalOut);
        }
    }
}
