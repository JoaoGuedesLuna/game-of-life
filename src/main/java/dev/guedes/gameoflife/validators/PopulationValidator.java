package dev.guedes.gameoflife.validators;

import com.google.inject.Inject;
import com.google.inject.assistedinject.Assisted;

import dev.guedes.gameoflife.exceptions.InvalidPopulationException;

/*
 * Copyright (c) 2026 João Guedes
 * Project : Game of Life
 * File : PopulationValidator.java
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
public class PopulationValidator implements Validator {
    private final int width;
    private final int height;

    @Inject
    public PopulationValidator(@Assisted("width") int width, @Assisted("height") int height) {
        this.width  = width;
        this.height = height;
    }

    @Override
    public void validate(Object value) {
        if (!(value instanceof String population)) throw new InvalidPopulationException();

        int rowCount = 1;
        int currentRowLength = 0;

        for (int i = 0; i < population.length(); i++) {
            char c = population.charAt(i);

            if (isRowSeparator(c)) {
                currentRowLength = 0;
                rowCount = incrementRowCount(rowCount);
            } else if (isCell(c)) {
                currentRowLength = incrementRowLength(currentRowLength);
            } else {
                throw new InvalidPopulationException();
            }
        }
    }

    private boolean isRowSeparator(char c) { return c == '#'; }

    private boolean isCell(char c) { return c == '0' || c == '1'; }

    private int incrementRowLength(int length) {
        int newLength = length + 1;

        if (newLength > width) throw new InvalidPopulationException();

        return newLength;
    }

    private int incrementRowCount(int count) {
        int newCount = count + 1;

        if (newCount > height) throw new InvalidPopulationException();

        return newCount;
    }
}
