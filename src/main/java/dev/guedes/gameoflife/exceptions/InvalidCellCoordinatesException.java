package dev.guedes.gameoflife.exceptions;

/*
 * Copyright (c) 2026 João Guedes
 * Project : Game of Life
 * File : InvalidCellCoordinatesException.java
 * Created on : 2026-11-09 17:00
 * Last modified: 2026-11-09 17:00
 *
 * Author : João Guedes
 * GitHub : https://github.com/JoaoGuedesLuna
 *
 * Copyright (c) 2026 João Guedes. All Rights Reserved.
 * Licensed under the MIT License.
 *
 * https://opensource.org/licenses/MIT
 */
public class InvalidCellCoordinatesException extends RuntimeException {
    public InvalidCellCoordinatesException(int row, int column, int width, int height) {
        super(String.format(
                "Invalid cell coordinates: row=%d, column=%d. Grid size is %dx%d.",
                row, column, height, width
        ));
    }
}
