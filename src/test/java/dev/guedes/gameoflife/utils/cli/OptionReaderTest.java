package dev.guedes.gameoflife.utils.cli;

import dev.guedes.gameoflife.models.ViewOption;
import dev.guedes.gameoflife.validators.Validator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Unit tests for {@link OptionReader} using Mockito.
 *
 * @author João Guedes
 */
@ExtendWith(MockitoExtension.class)
class OptionReaderTest {
    @Mock private InputReader inputReader;
    @Mock private Validator validator;
    @InjectMocks private OptionReader optionReader;

    @Test
    void read_ShouldReturnSelectedOptionValue() {
        String header = "Header";
        String prompt = "Prompt";

        ViewOption<String> option1 = new ViewOption<>("One", () -> "1");
        ViewOption<String> option2 = new ViewOption<>("Two", () -> "2");
        List<ViewOption<String>> options = List.of(option1, option2);

        when(inputReader.readInt(prompt, validator)).thenReturn(Optional.of(1));

        String result = optionReader.read(header, prompt, options, validator);

        assertEquals("1", result);

        verify(inputReader).readInt(prompt, validator);
    }

    @Test
    void read_ShouldReturnSecondOptionValue_WhenInputIsTwo() {
        String header = "Header";
        String prompt = "Prompt";

        ViewOption<String> option1 = new ViewOption<>("One", () -> "1");
        ViewOption<String> option2 = new ViewOption<>("Two", () -> "2");
        List<ViewOption<String>> options = List.of(option1, option2);

        when(inputReader.readInt(prompt, validator)).thenReturn(Optional.of(2));

        String result = optionReader.read(header, prompt, options, validator);

        assertEquals("2", result);

        verify(inputReader).readInt(prompt, validator);
    }

    @Test
    void read_ShouldWorkWithNullHeader() {
        String prompt = "Prompt";

        ViewOption<String> option1 = new ViewOption<>("One", () -> "1");
        ViewOption<String> option2 = new ViewOption<>("Two", () -> "2");
        List<ViewOption<String>> options = List.of(option1, option2);

        when(inputReader.readInt(prompt, validator)).thenReturn(Optional.of(2));

        String result = optionReader.read(prompt, options, validator);

        assertEquals("2", result);

        verify(inputReader).readInt(prompt, validator);
    }

    @Test
    void read_ShouldWorkWithBlankHeader() {
        String prompt = "Prompt";

        ViewOption<String> option1 = new ViewOption<>("One", () -> "1");
        ViewOption<String> option2 = new ViewOption<>("Two", () -> "2");
        List<ViewOption<String>> options = List.of(option1, option2);

        when(inputReader.readInt(prompt, validator)).thenReturn(Optional.of(2));

        String result = optionReader.read("", prompt, options, validator);

        assertEquals("2", result);

        verify(inputReader).readInt(prompt, validator);
    }
}
