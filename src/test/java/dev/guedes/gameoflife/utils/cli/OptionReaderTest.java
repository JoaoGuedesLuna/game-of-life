package dev.guedes.gameoflife.utils.cli;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.List;
import java.util.Optional;

import dev.guedes.gameoflife.validators.BoundedNumberValidator;
import dev.guedes.gameoflife.validators.BoundedNumberValidatorFactory;
import dev.guedes.gameoflife.views.cli.component.CLIOption;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/*
 * Copyright (c) 2026 João Guedes
 * Project : Game of Life
 * File : OptionReaderTest.java
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
@ExtendWith(MockitoExtension.class)
class OptionReaderTest {
    @Mock private InputReader inputReader;
    @Mock private BoundedNumberValidatorFactory boundedNumberValidatorFactory;
    @Mock private BoundedNumberValidator validator;
    @InjectMocks private OptionReader optionReader;

    @Test
    void read_ShouldReturnSelectedOptionValue() {
        String header = "Header";
        String prompt = "Prompt";

        CLIOption<String> option1 = new CLIOption<>("One", () -> "1");
        CLIOption<String> option2 = new CLIOption<>("Two", () -> "2");
        List<CLIOption<String>> options = List.of(option1, option2);

        when(boundedNumberValidatorFactory.create(1, 2)).thenReturn(validator);
        when(inputReader.readInt(prompt, validator)).thenReturn(Optional.of(1));

        String result = optionReader.read(header, prompt, options);

        assertEquals("1", result);

        verify(inputReader).readInt(prompt, validator);
    }

    @Test
    void read_ShouldReturnSecondOptionValue_WhenInputIsTwo() {
        String header = "Header";
        String prompt = "Prompt";

        CLIOption<String> option1 = new CLIOption<>("One", () -> "1");
        CLIOption<String> option2 = new CLIOption<>("Two", () -> "2");
        List<CLIOption<String>> options = List.of(option1, option2);

        when(boundedNumberValidatorFactory.create(1, 2)).thenReturn(validator);
        when(inputReader.readInt(prompt, validator)).thenReturn(Optional.of(2));

        String result = optionReader.read(header, prompt, options);

        assertEquals("2", result);

        verify(inputReader).readInt(prompt, validator);
    }

    @Test
    void read_ShouldWorkWithNullHeader() {
        String prompt = "Prompt";

        CLIOption<String> option1 = new CLIOption<>("One", () -> "1");
        CLIOption<String> option2 = new CLIOption<>("Two", () -> "2");
        List<CLIOption<String>> options = List.of(option1, option2);

        when(boundedNumberValidatorFactory.create(1, 2)).thenReturn(validator);
        when(inputReader.readInt(prompt, validator)).thenReturn(Optional.of(2));

        String result = optionReader.read(prompt, options);

        assertEquals("2", result);

        verify(inputReader).readInt(prompt, validator);
    }

    @Test
    void read_ShouldWorkWithBlankHeader() {
        String prompt = "Prompt";

        CLIOption<String> option1 = new CLIOption<>("One", () -> "1");
        CLIOption<String> option2 = new CLIOption<>("Two", () -> "2");
        List<CLIOption<String>> options = List.of(option1, option2);

        when(boundedNumberValidatorFactory.create(1, 2)).thenReturn(validator);
        when(inputReader.readInt(prompt, validator)).thenReturn(Optional.of(2));

        String result = optionReader.read("", prompt, options);

        assertEquals("2", result);

        verify(inputReader).readInt(prompt, validator);
    }
}
