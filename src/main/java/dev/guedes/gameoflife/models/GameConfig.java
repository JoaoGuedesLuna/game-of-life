package dev.guedes.gameoflife.models;

/*
 * Copyright (c) 2026 João Guedes
 * Project : Game of Life
 * File : GameConfig.java
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
public record GameConfig(int width, int height, int generations, int speed, String population) {}
