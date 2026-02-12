package dev.guedes.gameoflife.exceptions;

/**
 * Exception thrown when the grid dimensions are outside the allowed limits.
 *
 * @author João Guedes
 */
public class InvalidGridDimensionsException extends RuntimeException {
    public InvalidGridDimensionsException(String dimension, int value, int min, int max) {
        super(String.format(
                "Invalid %s: %d. Must be between %d and %d.",
                dimension, value, min, max
        ));
    }
}