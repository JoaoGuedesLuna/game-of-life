package dev.guedes.gameoflife.validators;

import com.google.inject.assistedinject.Assisted;

/*
 * Copyright (c) 2026 João Guedes
 * Project : Game of Life
 * File : PopulationValidatorFactory.java
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
public interface PopulationValidatorFactory {
    PopulationValidator create(@Assisted("width") int width, @Assisted("height") int height);
}
