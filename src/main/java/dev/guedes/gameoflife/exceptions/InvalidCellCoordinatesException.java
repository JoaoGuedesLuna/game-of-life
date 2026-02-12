package dev.guedes.gameoflife.exceptions;

/**
 * Exception thrown when trying to access a cell outside the grid bounds.
 *
 * @author João Guedes
 */
public class InvalidCellCoordinatesException extends RuntimeException {
    public InvalidCellCoordinatesException(int row, int column, int width, int height) {
        super(String.format(
                "Invalid cell coordinates: row=%d, column=%d. Grid size is %dx%d.",
                row, column, height, width
        ));
    }
}
