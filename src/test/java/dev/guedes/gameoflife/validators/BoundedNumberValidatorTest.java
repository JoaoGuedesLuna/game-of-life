package dev.guedes.gameoflife.validators;

import com.google.inject.Inject;
import dev.guedes.gameoflife.exceptions.InvalidBoundedNumberException;
import extensions.GuiceJUnit5Extension;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * Test class for {@link BoundedNumberValidator}.
 * Verifies that the validator correctly identifies valid and invalid numeric values.
 * Checks if values fall within the specified minimum and maximum bounds.
 *
 * @author João Guedes
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
