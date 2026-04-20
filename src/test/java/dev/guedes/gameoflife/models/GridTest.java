package dev.guedes.gameoflife.models;

import dev.guedes.gameoflife.config.GameLimits;
import dev.guedes.gameoflife.enums.Cell;
import dev.guedes.gameoflife.exceptions.InvalidCellCoordinatesException;
import dev.guedes.gameoflife.exceptions.InvalidGridDimensionsException;
import org.junit.jupiter.api.Test;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Test class for {@link Grid}. Ensures correct behavior of the Game of Life grid, including cell access, boundary
 * validation and evolution rules across generations.
 *
 * @author João Guedes
 */
class GridTest {
    @Test
    void constructor_ShouldThrowException_WhenCellsIsNull() {
        assertThrows(IllegalArgumentException.class, () -> new Grid(null));
    }

    @Test
    void constructor_ShouldThrowException_WhenCellsIsEmpty() {
        assertThrows(IllegalArgumentException.class, () -> new Grid(new Cell[][]{}));
    }

    @Test
    void constructor_ShouldThrowException_WhenFirstRowIsNull() {
        Cell[][] cells = {
                null,
                {Cell.ALIVE, Cell.DEAD, Cell.ALIVE},
                {Cell.DEAD, Cell.DEAD, Cell.DEAD}
        };

        assertThrows(IllegalArgumentException.class, () -> new Grid(cells));
    }

    @Test
    void constructor_ShouldThrowException_WhenSecondRowIsNull() {
        Cell[][] cells = {
                {Cell.DEAD, Cell.ALIVE, Cell.DEAD},
                null,
                {Cell.DEAD, Cell.DEAD, Cell.DEAD}
        };

        assertThrows(IllegalArgumentException.class, () -> new Grid(cells));
    }

    @Test
    void constructor_ShouldThrowException_WhenRowsHaveDifferentSizes() {
        Cell[][] cells = {
                {Cell.DEAD, Cell.ALIVE, Cell.DEAD},
                {Cell.ALIVE, Cell.DEAD, Cell.ALIVE},
                {Cell.DEAD, Cell.DEAD, Cell.DEAD, Cell.ALIVE}
        };

        assertThrows(IllegalArgumentException.class, () -> new Grid(cells));
    }

    @Test
    void constructor_ShouldThrowException_WhenWidthIsBelowMinimum() {
        Cell[][] cells = new Cell[GameLimits.MIN_HEIGHT][GameLimits.MIN_WIDTH - 1];

        for (Cell[] cell : cells) Arrays.fill(cell, Cell.DEAD);

        assertThrows(InvalidGridDimensionsException.class, () -> new Grid(cells));
    }

    @Test
    void constructor_ShouldThrowException_WhenWidthIsAboveMaximum() {
        Cell[][] cells = new Cell[GameLimits.MIN_HEIGHT][GameLimits.MAX_WIDTH + 1];

        for (Cell[] row : cells) Arrays.fill(row, Cell.DEAD);

        assertThrows(InvalidGridDimensionsException.class, () -> new Grid(cells));
    }

    @Test
    void constructor_ShouldThrowException_WhenHeightIsBelowMinimum() {
        Cell[][] cells = new Cell[GameLimits.MIN_HEIGHT - 1][GameLimits.MIN_WIDTH];

        for (Cell[] cell : cells) Arrays.fill(cell, Cell.DEAD);

        assertThrows(InvalidGridDimensionsException.class, () -> new Grid(cells));
    }

    @Test
    void constructor_ShouldThrowException_WhenHeightIsAboveMaximum() {
        Cell[][] cells = new Cell[GameLimits.MAX_HEIGHT + 1][GameLimits.MIN_WIDTH];

        for (Cell[] row : cells) Arrays.fill(row, Cell.DEAD);

        assertThrows(InvalidGridDimensionsException.class, () -> new Grid(cells));
    }

    @Test
    void constructor_ShouldSetCorrectWidthAndHeight_WhenWithinLimits() {
        Cell[][] cells = new Cell[GameLimits.MIN_HEIGHT][GameLimits.MIN_WIDTH];

        for (Cell[] cell : cells) Arrays.fill(cell, Cell.DEAD);

        Grid grid = new Grid(cells);

        assertEquals(3, grid.getWidth());
        assertEquals(3, grid.getHeight());
    }

    @Test
    void getCell_ShouldThrowException_WhenCoordinatesAreOutOfBounds() {
        Cell[][] cells = new Cell[GameLimits.MIN_HEIGHT][GameLimits.MIN_WIDTH];

        for (Cell[] cell : cells) Arrays.fill(cell, Cell.DEAD);

        Grid grid = new Grid(cells);

        int height = grid.getHeight();
        int width = grid.getWidth();

        assertThrows(InvalidCellCoordinatesException.class, () -> grid.getCell(-1, 0));
        assertThrows(InvalidCellCoordinatesException.class, () -> grid.getCell(height, 0));
        assertThrows(InvalidCellCoordinatesException.class, () -> grid.getCell(0, -1));
        assertThrows(InvalidCellCoordinatesException.class, () -> grid.getCell(0, width));
    }

    @Test
    void getCell_ShouldReturnCorrectCell_WhenCoordinatesAreValid() {
        Cell[][] cells = {
                {Cell.ALIVE, Cell.ALIVE, Cell.DEAD},
                {Cell.ALIVE, Cell.DEAD, Cell.DEAD},
                {Cell.DEAD, Cell.DEAD, Cell.DEAD}
        };

        Grid grid = new Grid(cells);

        assertEquals(Cell.ALIVE, grid.getCell(0, 0));
        assertEquals(Cell.ALIVE, grid.getCell(0, 1));
        assertEquals(Cell.DEAD, grid.getCell(0, 2));
        assertEquals(Cell.ALIVE, grid.getCell(1, 0));
        assertEquals(Cell.DEAD, grid.getCell(1, 1));
        assertEquals(Cell.DEAD, grid.getCell(1, 2));
        assertEquals(Cell.DEAD, grid.getCell(2, 0));
        assertEquals(Cell.DEAD, grid.getCell(2, 1));
        assertEquals(Cell.DEAD, grid.getCell(2, 2));
    }

    @Test
    void toggleCell_ShouldThrowException_WhenCoordinatesAreOutOfBounds() {
        Cell[][] cells = new Cell[GameLimits.MIN_HEIGHT][GameLimits.MIN_WIDTH];

        for (Cell[] row : cells) Arrays.fill(row, Cell.DEAD);

        Grid grid = new Grid(cells);

        int height = grid.getHeight();
        int width = grid.getWidth();

        assertThrows(InvalidCellCoordinatesException.class, () -> grid.toggleCell(-1, 0));
        assertThrows(InvalidCellCoordinatesException.class, () -> grid.toggleCell(height, 0));
        assertThrows(InvalidCellCoordinatesException.class, () -> grid.toggleCell(0, -1));
        assertThrows(InvalidCellCoordinatesException.class, () -> grid.toggleCell(0, width));
    }

    @Test
    void toggleCell_ShouldTurnCellAlive_WhenCellIsDead() {
        Cell[][] cells = new Cell[GameLimits.MIN_HEIGHT][GameLimits.MIN_WIDTH];

        for (Cell[] cell : cells) Arrays.fill(cell, Cell.DEAD);

        Grid grid = new Grid(cells);

        grid.toggleCell(1, 1);

        assertEquals(Cell.ALIVE, grid.getCell(1, 1));
        assertTrue(grid.isCellAlive(1, 1));
    }

    @Test
    void toggleCell_ShouldTurnCellDead_WhenCellIsAlive() {
        Cell[][] cells = new Cell[GameLimits.MIN_HEIGHT][GameLimits.MIN_WIDTH];

        for (Cell[] cell : cells) Arrays.fill(cell, Cell.ALIVE);

        Grid grid = new Grid(cells);

        grid.toggleCell(1, 1);

        assertEquals(Cell.DEAD, grid.getCell(1, 1));
        assertFalse(grid.isCellAlive(1, 1));
    }

    @Test
    void toggleCell_ShouldOnlyAffectTargetCell() {
        Cell[][] cells = {
                {Cell.DEAD, Cell.DEAD, Cell.DEAD},
                {Cell.DEAD, Cell.DEAD, Cell.DEAD},
                {Cell.DEAD, Cell.DEAD, Cell.DEAD}
        };

        Grid grid = new Grid(cells);

        grid.toggleCell(1, 1);

        assertEquals(Cell.DEAD, grid.getCell(0, 0));
        assertEquals(Cell.DEAD, grid.getCell(0, 1));
        assertEquals(Cell.DEAD, grid.getCell(0, 2));
        assertEquals(Cell.DEAD, grid.getCell(1, 0));
        assertEquals(Cell.ALIVE, grid.getCell(1, 1));
        assertEquals(Cell.DEAD, grid.getCell(1, 2));
        assertEquals(Cell.DEAD, grid.getCell(2, 0));
        assertEquals(Cell.DEAD, grid.getCell(2, 1));
        assertEquals(Cell.DEAD, grid.getCell(2, 2));
    }

    @Test
    void isCellAlive_ShouldReturnTrue_WhenCellIsAlive() {
        Cell[][] cells = {
                {Cell.DEAD, Cell.DEAD, Cell.DEAD},
                {Cell.DEAD, Cell.ALIVE, Cell.DEAD},
                {Cell.DEAD, Cell.DEAD, Cell.DEAD}
        };

        Grid grid = new Grid(cells);

        assertTrue(grid.isCellAlive(1, 1));
    }

    @Test
    void isCellAlive_ShouldReturnFalse_WhenCellIsDead() {
        Cell[][] cells = new Cell[GameLimits.MIN_HEIGHT][GameLimits.MIN_WIDTH];

        for (Cell[] cell : cells) Arrays.fill(cell, Cell.DEAD);

        Grid grid = new Grid(cells);

        assertFalse(grid.isCellAlive(1, 1));
    }

    @Test
    void hasLivingCells_ShouldReturnFalse_WhenAllCellsAreDead() {
        Cell[][] cells = new Cell[GameLimits.MIN_HEIGHT][GameLimits.MIN_WIDTH];

        for (Cell[] row : cells) Arrays.fill(row, Cell.DEAD);

        Grid grid = new Grid(cells);

        assertFalse(grid.hasLivingCells());
    }

    @Test
    void hasLivingCells_ShouldReturnTrue_WhenAtLeastOneCellIsAlive() {
        Cell[][] cells = new Cell[GameLimits.MIN_HEIGHT][GameLimits.MIN_WIDTH];

        for (Cell[] row : cells)Arrays.fill(row, Cell.DEAD);

        cells[1][1] = Cell.ALIVE;

        Grid grid = new Grid(cells);

        assertTrue(grid.hasLivingCells());
    }

    @Test
    void hasLivingCells_ShouldReturnTrue_WhenMultipleCellsAreAlive() {
        Cell[][] cells = {
                {Cell.ALIVE, Cell.DEAD, Cell.DEAD},
                {Cell.DEAD, Cell.ALIVE, Cell.DEAD},
                {Cell.DEAD, Cell.DEAD, Cell.ALIVE}
        };

        Grid grid = new Grid(cells);

        assertTrue(grid.hasLivingCells());
    }

    @Test
    void update_ShouldKillLonelyCell() {
        Cell[][] cells = {
                {Cell.DEAD, Cell.DEAD, Cell.DEAD},
                {Cell.DEAD, Cell.ALIVE, Cell.DEAD},
                {Cell.DEAD, Cell.DEAD, Cell.DEAD}
        };

        Grid grid = new Grid(cells);
        grid.update();

        assertEquals(Cell.DEAD, grid.getCell(1, 1));
    }

    @Test
    void update_ShouldKeepCellAlive_WithTwoNeighbors() {
        Cell[][] cells = {
                {Cell.DEAD, Cell.ALIVE, Cell.DEAD},
                {Cell.ALIVE, Cell.ALIVE, Cell.DEAD},
                {Cell.DEAD, Cell.DEAD, Cell.DEAD}
        };

        Grid grid = new Grid(cells);
        grid.update();

        assertEquals(Cell.ALIVE, grid.getCell(1, 1));
    }

    @Test
    void update_ShouldReviveDeadCell_WithExactlyThreeNeighbors() {
        Cell[][] cells = {
                {Cell.ALIVE, Cell.ALIVE, Cell.DEAD},
                {Cell.ALIVE, Cell.DEAD, Cell.DEAD},
                {Cell.DEAD, Cell.DEAD, Cell.DEAD}
        };

        Grid grid = new Grid(cells);
        grid.update();

        assertEquals(Cell.ALIVE, grid.getCell(1, 1));
    }

    @Test
    void saveSnapshot_ShouldStoreCurrentStateIndependently() {
        Cell[][] cells = {
                {Cell.ALIVE, Cell.DEAD, Cell.DEAD},
                {Cell.DEAD, Cell.ALIVE, Cell.DEAD},
                {Cell.DEAD, Cell.DEAD, Cell.ALIVE}
        };
        Grid grid = new Grid(cells);

        grid.toggleCell(0, 0);
        grid.saveSnapshot();

        grid.toggleCell(0, 0);
        grid.restoreSnapshot();

        assertEquals(Cell.DEAD, grid.getCell(0, 0));
    }

    @Test
    void restoreSnapshot_ShouldRevertGridToInitialState_WhenCalledAfterUpdate() {
        Cell[][] cells = {
                {Cell.ALIVE, Cell.ALIVE, Cell.ALIVE},
                {Cell.DEAD, Cell.DEAD, Cell.DEAD},
                {Cell.DEAD, Cell.DEAD, Cell.DEAD}
        };
        Grid grid = new Grid(cells);

        grid.update();

        assertEquals(Cell.DEAD, grid.getCell(0, 0));
        assertEquals(Cell.DEAD, grid.getCell(0, 2));
        assertEquals(Cell.ALIVE, grid.getCell(1, 1));

        grid.restoreSnapshot();

        assertEquals(Cell.ALIVE, grid.getCell(0, 0));
        assertEquals(Cell.ALIVE, grid.getCell(0, 2));
        assertEquals(Cell.DEAD, grid.getCell(1, 1));
    }

    @Test
    void clear_ShouldSetAllCellsToDead() {
        Cell[][] cells = {
                {Cell.ALIVE, Cell.DEAD, Cell.ALIVE},
                {Cell.ALIVE, Cell.ALIVE, Cell.DEAD},
                {Cell.DEAD, Cell.ALIVE, Cell.ALIVE}
        };

        Grid grid = new Grid(cells);

        assertTrue(grid.hasLivingCells());

        grid.clear();

        for (int row = 0; row < grid.getHeight(); row++) {
            for (int col = 0; col < grid.getWidth(); col++) {
                assertEquals(Cell.DEAD, grid.getCell(row, col));
            }
        }

        assertFalse(grid.hasLivingCells());
    }
}
