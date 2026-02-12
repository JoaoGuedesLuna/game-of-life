package dev.guedes.gameoflife.exceptions;

/**
 * Exception thrown when a population string is invalid.
 * This can happen if it contains invalid characters or exceeds board dimensions.
 *
 * @author João Guedes
 */
public class InvalidPopulationException extends RuntimeException {
    public InvalidPopulationException() {
        super("Invalid population. Only '0', '1', or '#' are allowed, and it must fit within the board dimensions.");
    }
}
