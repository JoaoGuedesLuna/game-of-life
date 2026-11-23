package dev.guedes.gameoflife.utils.cli;

import com.google.inject.Inject;
import java.util.List;

import dev.guedes.gameoflife.validators.BoundedNumberValidatorFactory;
import dev.guedes.gameoflife.validators.Validator;
import dev.guedes.gameoflife.views.cli.component.CLIOption;

/*
 * Copyright (c) 2026 João Guedes
 * Project : Game of Life
 * File : OptionReader.java
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
public class OptionReader {
    private final InputReader inputReader;
    private final BoundedNumberValidatorFactory boundedNumberValidatorFactory;

    @Inject
    public OptionReader(
            InputReader inputReader,
            BoundedNumberValidatorFactory boundedNumberValidatorFactory
    ) {
        this.inputReader = inputReader;
        this.boundedNumberValidatorFactory = boundedNumberValidatorFactory;
    }

    public <T> T read(String header, String inputPrompt, List<CLIOption<T>> options) {
        if (header != null && !header.isBlank()) System.out.println(header);

        for (int i = 0; i < options.size(); i++) {
            System.out.printf("%d - %s%n", (i + 1), options.get(i).label());
        }

        Validator optionsValidator = this.boundedNumberValidatorFactory.create(1, options.size());

        int choice = inputReader.readInt(inputPrompt, optionsValidator).get();

        return options.get(choice - 1).action().get();
    }

    public <T> T read(String inputPrompt, List<CLIOption<T>> options) {
        return read(null, inputPrompt, options);
    }
}
