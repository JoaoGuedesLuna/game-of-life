package dev.guedes.gameoflife.validators;

import com.google.inject.Inject;
import com.google.inject.assistedinject.Assisted;
import dev.guedes.gameoflife.exceptions.InvalidPopulationException;
import java.util.regex.Pattern;

/**
 * Validator that checks if a population string is valid for a Game of Life board.
 * Valid characters are '0', '1', and '#' (for row separators).
 * Also ensures the population does not exceed board dimensions.
 *
 * @author João Guedes
 */
public class PopulationValidator implements Validator {
    private static final Pattern VALID_PATTERN = Pattern.compile("^[01#]*$");

    private final int width;
    private final int height;

    @Inject
    public PopulationValidator(@Assisted("width") int width, @Assisted("height") int height) {
        this.width  = width;
        this.height = height;
    }

    @Override
    public void validate(Object value) {
        if (!(value instanceof String population)) throw new InvalidPopulationException();

        if (!VALID_PATTERN.matcher(population).matches()) throw new InvalidPopulationException();

        String[] rows = population.split("#");
        if (rows.length > height) throw new InvalidPopulationException();

        for (String row : rows) {
            if (row.length() > width) throw new InvalidPopulationException();
        }
    }
}
