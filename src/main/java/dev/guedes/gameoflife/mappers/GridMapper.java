package dev.guedes.gameoflife.mappers;

import dev.guedes.gameoflife.models.Grid;

/*
 * Copyright (c) 2026 João Guedes
 * Project : Game of Life
 * File : GridMapper.java
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
public interface GridMapper {
    Grid toGrid(int width, int height, String population);
}
