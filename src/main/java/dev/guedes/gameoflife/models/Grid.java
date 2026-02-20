package dev.guedes.gameoflife.models;

import dev.guedes.gameoflife.config.GameLimits;
import dev.guedes.gameoflife.enums.Cell;
import dev.guedes.gameoflife.exceptions.InvalidCellCoordinatesException;
import dev.guedes.gameoflife.exceptions.InvalidGridDimensionsException;
import lombok.Getter;

/**
 * Represents the grid for Conway's Game of Life.
 * Each cell can either be alive or dead, and the grid
 * evolves over discrete steps based on its neighbors.
 *
 * @author João Guedes
 */
public class Grid {
    @Getter private final int width;
    @Getter private final int height;

    private Cell[][] cells;
    private Cell[][] snapshot;

    public Grid(Cell[][] cells) {
        validateCellsMatrix(cells);

        this.height = cells.length;
        this.width = cells[0].length;
        this.cells = cells;

        saveSnapshot();
    }

    public Cell getCell(int row, int col) {
        if (!isWithinBounds(row, col)) {
            throw new InvalidCellCoordinatesException(row + 1, col + 1, width, height);
        }
        return cells[row][col];
    }

    public void update() {
        Cell[][] updatedCells = new Cell[height][width];

        for (int row = 0; row < height; row++) {
            for (int column = 0; column < width; column++) {
                updatedCells[row][column] = getNewCellState(row, column);
            }
        }

        cells = updatedCells;
    }

    public boolean isCellAlive(int row, int col) { return getCell(row, col) == Cell.ALIVE; }

    public void toggleCell(int row, int col) {
        if (!isWithinBounds(row, col)) {
            throw new InvalidCellCoordinatesException(row + 1, col + 1, width, height);
        }
        cells[row][col] = (cells[row][col] == Cell.ALIVE) ? Cell.DEAD : Cell.ALIVE;
    }

    public void saveSnapshot() {
        this.snapshot = new Cell[height][width];
        for (int i = 0; i < height; i++) {
            System.arraycopy(cells[i], 0, snapshot[i], 0, width);
        }
    }

    public void restoreSnapshot() {
        for (int i = 0; i < height; i++) {
            System.arraycopy(snapshot[i], 0, cells[i], 0, width);
        }
    }

    private Cell getNewCellState(int row, int col) {
        int livingNeighbors = countLivingNeighbors(row, col);
        if (livingNeighbors == 3) return Cell.ALIVE;

        boolean isAlive = isCellAlive(row, col);
        if (isAlive && livingNeighbors == 2) return Cell.ALIVE;

        return Cell.DEAD;
    }

    private int countLivingNeighbors(int row, int col) {
        int count = 0;

        for (int i = row - 1; i <= row + 1; i++) {
            for (int j = col - 1; j <= col + 1; j++) {
                if (isWithinBounds(i, j) && !(i == row && j == col) && isCellAlive(i, j)) {
                    count++;
                }
            }
        }

        return count;
    }

    private boolean isWithinBounds(int row, int col) {
        return row >= 0 && row < height && col >= 0 && col < width;
    }

    private void validateCellsMatrix(Cell[][] cells) {
        if (cells == null || cells.length == 0) {
            throw new IllegalArgumentException("Cells matrix must not be null or empty.");
        }

        if (cells[0] == null) {
            throw new IllegalArgumentException(
                    "Cells matrix must be rectangular and contain no null rows."
            );
        }

        int expectedWidth = cells[0].length;

        validateDimensions(expectedWidth, cells.length);

        for (Cell[] cell : cells) {
            if (cell == null || cell.length != expectedWidth) {
                throw new IllegalArgumentException(
                        "Cells matrix must be rectangular and contain no null rows."
                );
            }
        }
    }

    private void validateDimensions(int width, int height) {
        if (width < GameLimits.MIN_WIDTH || width > GameLimits.MAX_WIDTH) {
            throw new InvalidGridDimensionsException(
                    "Width", width, GameLimits.MIN_WIDTH, GameLimits.MAX_WIDTH
            );
        }

        if (height < GameLimits.MIN_HEIGHT || height > GameLimits.MAX_HEIGHT) {
            throw new InvalidGridDimensionsException(
                    "Height", height, GameLimits.MIN_HEIGHT, GameLimits.MAX_HEIGHT
            );
        }
    }
}
