package dev.guedes.gameoflife.exceptions;

/*
 * Copyright (c) 2026 João Guedes
 * Project : Game of Life
 * File : InvalidPopulationException.java
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
public class InvalidPopulationException extends RuntimeException {
    public InvalidPopulationException() {
        super("Invalid population. Only '0', '1', or '#' are allowed, and it must fit within the board dimensions.");
    }
}
