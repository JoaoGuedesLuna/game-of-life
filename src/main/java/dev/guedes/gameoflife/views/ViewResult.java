package dev.guedes.gameoflife.views;

import dev.guedes.gameoflife.enums.ViewAction;

/*
 * Copyright (c) 2026 João Guedes
 * Project : Game of Life
 * File : ViewResult.java
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
public record ViewResult<T>(ViewAction nextAction, T payload) {
    public static <T> ViewResult<T> of(ViewAction nextAction, T payload) { return new ViewResult<>(nextAction, payload); }
    public static <T> ViewResult<T> of(ViewAction nextAction) { return of(nextAction, null); }
}
