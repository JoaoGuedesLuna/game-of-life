package dev.guedes.gameoflife.validators;

import com.google.inject.Inject;
import com.google.inject.assistedinject.Assisted;
import dev.guedes.gameoflife.exceptions.InvalidBoundedNumberException;

/**
 * Validator that checks if a numeric value falls within specified bounds.
 * Ensures that a given number is between the minimum and maximum values (inclusive).
 * Throws an InvalidBoundedNumberException if the value is outside the allowed range.
 *
 * @author João Guedes
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
