package dev.guedes.gameoflife.validators;

import com.google.inject.Inject;
import com.google.inject.assistedinject.Assisted;

import dev.guedes.gameoflife.exceptions.InvalidBoundedNumberException;

/*
 * Copyright (c) 2026 João Guedes
 * Project : Game of Life
 * File : BoundedNumberValidator.java
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
public class BoundedNumberValidator implements Validator {
    private final Number min;
    private final Number max;

    @Inject
    public BoundedNumberValidator(@Assisted("min") Number min, @Assisted("max") Number max) {
        this.min = min;
        this.max = max;
    }

    @Override
    public void validate(Object value) {
        if (!(value instanceof Number n)) throw new InvalidBoundedNumberException(min, max);

        double doubleValue = n.doubleValue();

        if (doubleValue < min.doubleValue() || doubleValue > max.doubleValue()) {
            throw new InvalidBoundedNumberException(min, max);
        }
    }
}
