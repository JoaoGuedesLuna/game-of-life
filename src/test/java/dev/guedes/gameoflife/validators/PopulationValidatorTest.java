package dev.guedes.gameoflife.validators;

import com.google.inject.Inject;
import dev.guedes.gameoflife.exceptions.InvalidPopulationException;
import extensions.GuiceJUnit5Extension;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * Test class for {@link PopulationValidator}.
 * Verifies that the validator correctly identifies valid and invalid populations.
 * Checks character validity and board dimensions.
 *
 * @author João Guedes
 */
@ExtendWith(GuiceJUnit5Extension.class)
class PopulationValidatorTest {
    private final PopulationValidator validator;

    @Inject
    PopulationValidatorTest(PopulationValidatorFactory validatorFactory) {
        this.validator = validatorFactory.create(5, 3);
    }

    @Test
    void validate_ShouldThrowException_WhenPopulationIsNull() {
        assertThrows(InvalidPopulationException.class, () -> validator.validate(null));
    }

    @Test
    void validate_ShouldThrowException_WhenPopulationHasInvalidCharacters() {
        assertThrows(InvalidPopulationException.class, () -> validator.validate("012#101"));
        assertThrows(InvalidPopulationException.class, () -> validator.validate("01a#101"));
    }

    @Test
    void validate_ShouldThrowException_WhenPopulationExceedsHeight() {
        assertThrows(InvalidPopulationException.class, () -> validator.validate("101#010#111#000"));
    }

    @Test
    void validate_ShouldThrowException_WhenPopulationExceedsWidth() {
        assertThrows(InvalidPopulationException.class, () -> validator.validate("101010#010#111"));
    }

    @Test
    void validate_ShouldNotThrowException_WhenPopulationIsValid() {
        assertDoesNotThrow(() -> validator.validate(""));
        assertDoesNotThrow(() -> validator.validate("101#"));
        assertDoesNotThrow(() -> validator.validate("#101"));
        assertDoesNotThrow(() -> validator.validate("##101"));
        assertDoesNotThrow(() -> validator.validate("1#0#1"));
        assertDoesNotThrow(() -> validator.validate("101#010#111"));
        assertDoesNotThrow(() -> validator.validate("10101#00000#11111"));
    }
}
