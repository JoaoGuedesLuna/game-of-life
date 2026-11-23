package dev.guedes.gameoflife.views.cli.component;

import java.util.function.Supplier;

/*
 * Copyright (c) 2026 João Guedes
 * Project : Game of Life
 * File : CLIOption.java
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
public record CLIOption<T>(String label, Supplier<T> action) {}
