package dev.guedes.gameoflife.mappers.impl;

import com.google.inject.Inject;
import dev.guedes.gameoflife.config.GameLimits;
import dev.guedes.gameoflife.enums.Cell;
import dev.guedes.gameoflife.mappers.GridMapper;
import dev.guedes.gameoflife.models.Grid;
import dev.guedes.gameoflife.validators.BoundedNumberValidator;
import dev.guedes.gameoflife.validators.BoundedNumberValidatorFactory;
import dev.guedes.gameoflife.validators.PopulationValidator;
import dev.guedes.gameoflife.validators.PopulationValidatorFactory;
import java.util.Arrays;

/**
 * Concrete implementation of {@link GridMapper}.
 *
 * @author João Guedes
 */
public class GridMapperImpl implements GridMapper {
    private final BoundedNumberValidator widthValidator;
    private final BoundedNumberValidator heightValidator;
    private final PopulationValidatorFactory populationValidatorFactory;

    @Inject
    private GridMapperImpl(
            BoundedNumberValidatorFactory boundedNumberValidatorFactory,
            PopulationValidatorFactory populationValidatorFactory
    ) {
        this.widthValidator =
                boundedNumberValidatorFactory.create(GameLimits.MIN_WIDTH, GameLimits.MAX_WIDTH);

        this.heightValidator =
                boundedNumberValidatorFactory.create(GameLimits.MIN_HEIGHT, GameLimits.MAX_HEIGHT);

        this.populationValidatorFactory = populationValidatorFactory;
    }

    public Grid toGrid(int width, int height, String population) {
        validateArguments(width, height, population);

        Cell[][] cells = initializeCells(width, height);
        populateCells(cells, population);

        return new Grid(cells);
    }

    private void validateArguments(int width, int height, String population) {
        widthValidator.validate(width);
        heightValidator.validate(height);

        PopulationValidator populationValidator = populationValidatorFactory.create(width, height);
        populationValidator.validate(population);
    }

    private Cell[][] initializeCells(int width, int height) {
        Cell[][] cells = new Cell[height][width];
        for (Cell[] row : cells) Arrays.fill(row, Cell.DEAD);
        return cells;
    }

    private void populateCells(Cell[][] cells, String population) {
        String[] rows = population.split("#");
        for (int row = 0; row < rows.length; row++) {
            String rowString = rows[row];
            for (int col = 0; col < rowString.length(); col++) {
                if (rowString.charAt(col) == '1') cells[row][col] = Cell.ALIVE;
            }

        }
    }
}
