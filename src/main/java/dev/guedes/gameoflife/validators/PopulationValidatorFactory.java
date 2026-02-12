package dev.guedes.gameoflife.validators;

import com.google.inject.assistedinject.Assisted;

/**
 * Factory interface for creating instances of {@link PopulationValidator}.
 *
 * @author João Guedes
 */
public interface PopulationValidatorFactory {
    PopulationValidator create(@Assisted("width") int width, @Assisted("height") int height);
}
