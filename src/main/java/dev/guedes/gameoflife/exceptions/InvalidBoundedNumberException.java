package dev.guedes.gameoflife.exceptions;

/**
 * Exception thrown when a number value is outside the specified bounds.
 *
 * @author João Guedes
 */
public class InvalidBoundedNumberException extends RuntimeException {
    public InvalidBoundedNumberException(Number min, Number max) {
        super(String.format("Value must be between %s and %s.", min, max));
    }
}
