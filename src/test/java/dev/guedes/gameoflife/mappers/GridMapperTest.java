package dev.guedes.gameoflife.mappers;

import com.google.inject.Inject;
import dev.guedes.gameoflife.enums.Cell;
import dev.guedes.gameoflife.exceptions.InvalidBoundedNumberException;
import dev.guedes.gameoflife.exceptions.InvalidPopulationException;
import dev.guedes.gameoflife.models.Grid;
import extensions.GuiceJUnit5Extension;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import static dev.guedes.gameoflife.config.GameLimits.MAX_HEIGHT;
import static dev.guedes.gameoflife.config.GameLimits.MAX_WIDTH;
import static dev.guedes.gameoflife.config.GameLimits.MIN_HEIGHT;
import static dev.guedes.gameoflife.config.GameLimits.MIN_WIDTH;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * Test class for {@link GridMapper}.
 * Ensures correct mapping from textual population to {@link Grid}.
 *
 * @author João
 */
@ExtendWith(GuiceJUnit5Extension.class)
class GridMapperTest {
    private final GridMapper gridMapper;

    @Inject
    GridMapperTest(GridMapper gridMapper) { this.gridMapper = gridMapper; }

    @Test
    void toGrid_ShouldThrowException_WhenWidthIsBelowMinimum() {
        assertThrows(InvalidBoundedNumberException.class, () ->
                gridMapper.toGrid(MIN_WIDTH - 1, MIN_HEIGHT, "")
        );
    }

    @Test
    void toGrid_ShouldThrowException_WhenWidthIsAboveMaximum() {
        assertThrows(InvalidBoundedNumberException.class, () ->
                gridMapper.toGrid(MAX_WIDTH + 1, MIN_HEIGHT, "")
        );
    }

    @Test
    void toGrid_ShouldThrowException_WhenHeightIsBelowMinimum() {
        assertThrows(InvalidBoundedNumberException.class, () ->
                gridMapper.toGrid(MIN_WIDTH, MIN_HEIGHT - 1, "")
        );
    }

    @Test
    void toGrid_ShouldThrowException_WhenHeightIsAboveMaximum() {
        assertThrows(InvalidBoundedNumberException.class, () ->
                gridMapper.toGrid(MIN_WIDTH, MAX_HEIGHT + 1, "")
        );
    }

    @Test
    void toGrid_ShouldThrowException_WhenPopulationContainsInvalidCharacters() {
        assertThrows(InvalidPopulationException.class, () ->
                gridMapper.toGrid(MIN_WIDTH, MIN_HEIGHT, "10A")
        );
    }

    @Test
    void toGrid_ShouldThrowException_WhenPopulationHasTooManyRows() {
        String population = "1#".repeat(MIN_HEIGHT + 1);
        assertThrows(InvalidPopulationException.class, () ->
                gridMapper.toGrid(MIN_WIDTH, MIN_HEIGHT, population)
        );
    }

    @Test
    void toGrid_ShouldThrowException_WhenPopulationRowIsTooLong() {
        String population = "1".repeat(MIN_WIDTH + 1);
        assertThrows(InvalidPopulationException.class, () ->
                gridMapper.toGrid(MIN_WIDTH, MIN_HEIGHT, population)
        );
    }

    @Test
    void toGrid_ShouldCreateAllDeadGrid_WhenPopulationIsEmpty() {
        int width = MIN_WIDTH;
        int height = MIN_HEIGHT;

        Grid grid = gridMapper.toGrid(width, height, "");

        assertEquals(width, grid.getWidth());
        assertEquals(height, grid.getHeight());

        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                assertEquals(Cell.DEAD, grid.getCell(i, j));
            }
        }
    }

    @Test
    void toGrid_ShouldCorrectlyMapCells_WhenPopulationIsValid() {
        int width = 3;
        int height = 3;

        String population = "101#01#";

        Grid grid = gridMapper.toGrid(width, height, population);

        assertEquals(Cell.ALIVE, grid.getCell(0, 0));
        assertEquals(Cell.DEAD,  grid.getCell(0, 1));
        assertEquals(Cell.ALIVE, grid.getCell(0, 2));
        assertEquals(Cell.DEAD,  grid.getCell(1, 0));
        assertEquals(Cell.ALIVE, grid.getCell(1, 1));
        assertEquals(Cell.DEAD,  grid.getCell(1, 2));
        assertEquals(Cell.DEAD,  grid.getCell(2, 0));
    }
}
