package dev.guedes.gameoflife.utils.cli;

import com.google.inject.Inject;
import java.util.Optional;
import java.util.Scanner;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

import dev.guedes.gameoflife.validators.Validator;

/*
 * Copyright (c) 2026 João Guedes
 * Project : Game of Life
 * File : InputReader.java
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
public class InputReader {
    private final Scanner scanner;

    @Inject
    public InputReader(Scanner scanner) { this.scanner = scanner; }

    public Optional<Integer> readInt(String prompt, Validator validator, Set<String> sentinelValues) {
        return readInput(prompt, validator, sentinelValues, Integer::parseInt);
    }

    public Optional<Integer> readInt(String prompt, Validator validator) {
        return readInt(prompt, validator, null);
    }

    public Optional<String> readString(String prompt, Validator validator, Set<String> sentinelValues) {
        return readInput(prompt, validator, sentinelValues, s -> s);
    }

    private <T> Optional<T> readInput(
            String prompt,
            Validator validator,
            Set<String> sentinelValues,
            Function<String, T> parser
    ) {
        Set<String> normalizedSentinels = normalizeSentinels(sentinelValues);

        while (true) {
            System.out.print(prompt);

            String input = scanner.nextLine().trim();

            if (normalizedSentinels.contains(input.toLowerCase())) return Optional.empty();

            try {
                T value = parser.apply(input);
                validator.validate(value);
                return Optional.of(value);
            } catch (NumberFormatException nfe) {
                System.out.println("Invalid input. Please enter a number.\n");
            } catch (Exception e) {
                System.out.println("Invalid input. " + e.getMessage() + "\n");
            }
        }
    }

    private Set<String> normalizeSentinels(Set<String> set) {
        if (set == null) return Set.of();

        return set.stream()
                .map(s -> s.trim().toLowerCase())
                .collect(Collectors.toSet());
    }
}
