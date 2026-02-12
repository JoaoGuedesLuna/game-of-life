package dev.guedes.gameoflife.utils.cli;

import com.google.inject.Inject;
import dev.guedes.gameoflife.models.ViewOption;
import dev.guedes.gameoflife.validators.Validator;
import java.util.List;

/**
 * Utility for presenting a list of options and capturing a validated user choice.
 *
 * @author João Guedes
 */
public class OptionReader {
    private final InputReader inputReader;

    @Inject
    public OptionReader(InputReader inputReader) { this.inputReader = inputReader; }

    public <T> T read(String header, String inputPrompt, List<ViewOption<T>> options, Validator validator) {
        if (header != null && !header.isBlank()) {
            System.out.println(header);
        }

        for (int i = 0; i < options.size(); i++) {
            System.out.printf("%d - %s%n", (i + 1), options.get(i).getLabel());
        }

        int choice = inputReader.readInt(inputPrompt, validator).get();

        return options.get(choice - 1).getAction().get();
    }

    public <T> T read(String inputPrompt, List<ViewOption<T>> options, Validator validator) {
        return read(null, inputPrompt, options, validator);
    }
}