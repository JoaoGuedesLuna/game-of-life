package dev.guedes.gameoflife.views.gui.styles;

import java.awt.Dimension;

import dev.guedes.gameoflife.utils.swing.ScreenUtils;

/*
 * Copyright (c) 2026 João Guedes
 * Project : Game of Life
 * File : GUIDimensions.java
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
public final class GUIDimensions {
    public static final int GRID_CELL_SIZE = 10;

    public static final int MAIN_VIEW_HEIGHT = ScreenUtils.getScreenHeight() - GRID_CELL_SIZE;
    public static final int MAIN_VIEW_WIDTH = ScreenUtils.getScreenWidth() + GRID_CELL_SIZE * 2;

    public static final int EXPLANATION_VIEW_HEIGHT = 400;
    public static final int EXPLANATION_VIEW_WIDTH = 680;

    public static final Dimension EXPLANATION_BTN_SIZE = new Dimension(150, 40);
    public static final Dimension NORMAL_BTN_SIZE = new Dimension(100, 40);
    public static final Dimension START_BTN_SIZE = new Dimension(140, 50);

    private GUIDimensions() {}
}
