package dev.guedes.gameoflife.validators;

import com.google.inject.Inject;
import extensions.GuiceJUnit5Extension;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import dev.guedes.gameoflife.exceptions.InvalidBoundedNumberException;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

/*
 * Copyright (c) 2026 João Guedes
 * Project : Game of Life
 * File : BoundedNumberValidatorTest.java
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
@ExtendWith(GuiceJUnit5Extension.class)
class BoundedNumberValidatorTest {
    private final BoundedNumberValidator validator;

    @Inject
    BoundedNumberValidatorTest(BoundedNumberValidatorFactory validatorFactory) {
        this.validator = validatorFactory.create(1, 10);
    }

    @Test
    void validate_ShouldThrowException_WhenNumberIsNull() {
        assertThrows(InvalidBoundedNumberException.class, () -> validator.validate(null));
    }

    @Test
    void validate_ShouldThrowException_WhenNumberIsBelowMinimum() {
        assertThrows(InvalidBoundedNumberException.class, () -> validator.validate(0.99));
    }

    @Test
    void validate_ShouldThrowException_WhenNumberIsAboveMaximum() {
        assertThrows(InvalidBoundedNumberException.class, () -> validator.validate(10.1));
    }

    @Test
    void validate_ShouldNotThrowException_WhenNumberIsAtMinimumBoundary() {
        assertDoesNotThrow(() -> validator.validate(1));
    }

    @Test
    void validate_ShouldNotThrowException_WhenNumberIsAtMaximumBoundary() {
        assertDoesNotThrow(() -> validator.validate(10));
    }

    @Test
    void validate_ShouldNotThrowException_WhenNumberIsWithinBounds() {
        assertDoesNotThrow(() -> validator.validate(1.1));
        assertDoesNotThrow(() -> validator.validate(5));
        assertDoesNotThrow(() -> validator.validate(9.99));
    }

    @Test
    void validate_ShouldHandleDifferentNumberTypes() {
        assertDoesNotThrow(() -> validator.validate(5));
        assertDoesNotThrow(() -> validator.validate(5L));
        assertDoesNotThrow(() -> validator.validate(5.0f));
        assertDoesNotThrow(() -> validator.validate(5.0));
    }
}
