package dev.guedes.gameoflife.utils.cli;

import dev.guedes.gameoflife.validators.Validator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.Optional;
import java.util.Scanner;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoInteractions;
import static org.mockito.Mockito.when;

/**
 * Unit tests for {@link InputReader} using Mockito to simulate terminal interaction.
 *
 * @author João Guedes
 */
@ExtendWith(MockitoExtension.class)
class InputReaderTest {
    @Mock private Scanner scanner;
    @Mock private Validator validator;
    @InjectMocks private InputReader inputReader;

    @Test
    void readInt_ShouldReturnInteger_WhenInputIsValid() {
        when(scanner.nextLine()).thenReturn("42");

        Optional<Integer> result = inputReader.readInt("Choose an option: ", validator);

        assertTrue(result.isPresent());
        assertEquals(42, result.get());

        verify(validator).validate(42);
    }

    @Test
    void readInt_ShouldRetry_WhenFirstInputIsInvalidFormat() {
        when(scanner.nextLine()).thenReturn("invalid", "10");

        Optional<Integer> result = inputReader.readInt("Choose an option: ", validator);

        assertTrue(result.isPresent());
        assertEquals(10, result.get());

        verify(scanner, times(2)).nextLine();
    }

    @Test
    void readInt_ShouldReturnEmpty_WhenSentinelValueIsEntered() {
        Set<String> sentinels = Set.of("QUIT");

        when(scanner.nextLine()).thenReturn("quit");

        Optional<Integer> result = inputReader.readInt("Choose an option: ", validator, sentinels);

        assertFalse(result.isPresent());

        verifyNoInteractions(validator);
    }

    @Test
    void readString_ShouldReturnValidatedAndTrimmedString() {
        when(scanner.nextLine()).thenReturn("  valid  ");

        Optional<String> result = inputReader.readString("Choose an option: ", validator, null);

        assertTrue(result.isPresent());
        assertEquals("valid", result.get());

        verify(validator).validate("valid");
    }

    @Test
    void readInput_ShouldHandleValidationExceptionAndRetry() {
        when(scanner.nextLine()).thenReturn("5", "15");

        doThrow(new RuntimeException("Value too low")).when(validator).validate(5);
        doNothing().when(validator).validate(15);

        Optional<Integer> result = inputReader.readInt("Choose an option: ", validator);

        assertTrue(result.isPresent());
        assertEquals(15, result.get());
    }
}
