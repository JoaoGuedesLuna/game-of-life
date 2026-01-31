package dev.guedes.gameoflife.validators;

/**
 * Interface for validating objects.
 * Implementations should perform validation logic and throw appropriate exceptions
 * when validation fails.
 *
 * @author João Guedes
 */
public interface Validator {
    void validate(Object value);
}
