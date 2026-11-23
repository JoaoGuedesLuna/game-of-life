package dev.guedes.gameoflife.config;

/*
 * Copyright (c) 2026 João Guedes
 * Project : Game of Life
 * File : GameLimits.java
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
public final class GameLimits {
    public static final int MIN_WIDTH = 3;
    public static final int MAX_WIDTH = 200;

    public static final int MIN_HEIGHT = 3;
    public static final int MAX_HEIGHT = 200;

    public static final int MIN_GENERATIONS = 1;
    public static final int MAX_GENERATIONS = 10_000;

    public static final int MIN_SPEED = 50;
    public static final int MAX_SPEED = 5_000;

    private GameLimits() {}
}
