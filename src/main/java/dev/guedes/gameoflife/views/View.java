package dev.guedes.gameoflife.views;

import dev.guedes.gameoflife.enums.ViewAction;

/*
 * Copyright (c) 2026 João Guedes
 * Project : Game of Life
 * File : View.java
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
public interface View {
    ViewResult<?> display();
    ViewAction getAction();
}
