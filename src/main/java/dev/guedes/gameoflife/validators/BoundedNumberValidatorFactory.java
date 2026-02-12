package dev.guedes.gameoflife.validators;

import com.google.inject.assistedinject.Assisted;

/**
 * Factory interface for creating instances of {@link BoundedNumberValidator}.
 *
 * @author João Guedes
 */
public interface BoundedNumberValidatorFactory {
    BoundedNumberValidator create(@Assisted("min") Number min, @Assisted("max") Number max);
}
