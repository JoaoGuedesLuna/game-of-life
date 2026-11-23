package dev.guedes.gameoflife.exceptions;

/*
 * Copyright (c) 2026 João Guedes
 * Project : Game of Life
 * File : InvalidGridDimensionsException.java
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
public class InvalidGridDimensionsException extends RuntimeException {
    public InvalidGridDimensionsException(String dimension, int value, int min, int max) {
        super(String.format("Invalid %s: %d. Must be between %d and %d.", dimension, value, min, max));
    }
}
