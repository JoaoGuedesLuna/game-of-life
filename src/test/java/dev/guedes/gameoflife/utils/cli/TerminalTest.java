package dev.guedes.gameoflife.utils.cli;

import org.junit.jupiter.api.Test;
import org.mockito.MockedConstruction;
import org.mockito.Mockito;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

/*
 * Copyright (c) 2026 João Guedes
 * Project : Game of Life
 * File : TerminalTest.java
 * Created on : 2026-11-09 17:00
 * Last modified: 2026-11-09 17:00
 *
 * Author : João Guedes
 * GitHub : https://github.com/JoaoGuedesLuna
 *
 * Copyright (c) 2026 João Guedes. All Rights Reserved.
 * Licensed under the MIT License.
 *
 * https://opensource.org/licenses/MIT
 */
class TerminalTest {
    @Test
    void constructor_ShouldThrowUnsupportedOperationException_WhenInstantiated() throws Exception {
        Constructor<Terminal> constructor = Terminal.class.getDeclaredConstructor();
        constructor.setAccessible(true);

        InvocationTargetException ex = assertThrows(InvocationTargetException.class, constructor::newInstance);
        assertInstanceOf(UnsupportedOperationException.class, ex.getCause());
    }

    @Test
    void clear_ShouldNotExecuteAnsiFallback_WhenSystemCommandSucceeds() {
        PrintStream originalOut = System.out;
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();

        try (MockedConstruction<ProcessBuilder> mockedBuilder = Mockito.mockConstruction(
                ProcessBuilder.class,
                (mock, context) -> {
                    Process mockProcess = Mockito.mock(Process.class);
                    Mockito.when(mock.inheritIO()).thenReturn(mock);
                    Mockito.when(mock.start()).thenReturn(mockProcess);
                    Mockito.when(mockProcess.waitFor()).thenReturn(0);
                }))
        {
            System.setOut(new PrintStream(outContent));

            assertDoesNotThrow(Terminal::clear);

            assertFalse(outContent.toString().contains("\033[H\033[2J"));
        } finally {
            System.setOut(originalOut);
        }
    }

    @Test
    void clear_ShouldExecuteAnsiFallback_WhenSystemCommandFails() {
        PrintStream originalOut = System.out;
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();

        try (MockedConstruction<ProcessBuilder> mockedBuilder = Mockito.mockConstruction(
                ProcessBuilder.class,
                (mock, context) -> {
                    Process mockProcess = Mockito.mock(Process.class);
                    Mockito.when(mock.inheritIO()).thenReturn(mock);
                    Mockito.when(mock.start()).thenReturn(mockProcess);
                    Mockito.when(mockProcess.waitFor()).thenReturn(1);
                }))
        {
            System.setOut(new PrintStream(outContent));

            assertDoesNotThrow(Terminal::clear);

            assertTrue(outContent.toString().contains("\033[H\033[2J"));
        } finally {
            System.setOut(originalOut);
        }
    }

    @Test
    void clear_ShouldExecuteAnsiFallback_WhenIoExceptionIsThrown() {
        PrintStream originalOut = System.out;
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();

        try (MockedConstruction<ProcessBuilder> mockedBuilder = Mockito.mockConstruction(
                ProcessBuilder.class,
                (mock, context) -> {
                    Mockito.when(mock.inheritIO()).thenReturn(mock);
                    Mockito.when(mock.start()).thenThrow(new IOException());
                }))
        {
            System.setOut(new PrintStream(outContent));

            assertDoesNotThrow(Terminal::clear);

            assertTrue(outContent.toString().contains("\033[H\033[2J"));
        } finally {
            System.setOut(originalOut);
        }
    }

    @Test
    void clear_ShouldUseWindowsCommand_WhenOsIsWindows() {
        String originalOs = System.getProperty("os.name");

        try (MockedConstruction<ProcessBuilder> mockedBuilder = Mockito.mockConstruction(
                ProcessBuilder.class,
                (mock, context) -> {
                    Process mockProcess = Mockito.mock(Process.class);
                    Mockito.when(mock.inheritIO()).thenReturn(mock);
                    Mockito.when(mock.start()).thenReturn(mockProcess);
                    Mockito.when(mockProcess.waitFor()).thenReturn(0);
                }))
        {
            System.setProperty("os.name", "Windows 11");

            assertDoesNotThrow(Terminal::clear);
        } finally {
            System.setProperty("os.name", originalOs);
        }
    }

    @Test
    void clear_ShouldUseUnixCommand_WhenOsIsLinuxOrMac() {
        String originalOs = System.getProperty("os.name");

        try (MockedConstruction<ProcessBuilder> mockedBuilder = Mockito.mockConstruction(
                ProcessBuilder.class,
                (mock, context) -> {
                    Process mockProcess = Mockito.mock(Process.class);
                    Mockito.when(mock.inheritIO()).thenReturn(mock);
                    Mockito.when(mock.start()).thenReturn(mockProcess);
                    Mockito.when(mockProcess.waitFor()).thenReturn(0);
                }))
        {
            System.setProperty("os.name", "Linux");

            assertDoesNotThrow(Terminal::clear);
        } finally {
            System.setProperty("os.name", originalOs);
        }
    }

    @Test
    void pause_ShouldDisplayCustomMessage_WhenCalled() {
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
