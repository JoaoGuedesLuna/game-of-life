package dev.guedes.gameoflife.exceptions;

/*
 * Copyright (c) 2026 João Guedes
 * Project : Game of Life
 * File : InvalidBoundedNumberException.java
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
public class InvalidBoundedNumberException extends RuntimeException {
    public InvalidBoundedNumberException(Number min, Number max) {
        super(String.format("Value must be between %s and %s.", min, max));
    }
}
