package dev.guedes.gameoflife.views.gui.styles;

import java.awt.Font;

/*
 * Copyright (c) 2026 João Guedes
 * Project : Game of Life
 * File : GUITypography.java
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
public final class GUITypography {
    public static final String FONT_FAMILY = "SansSerif";

    public static final Font TITLE_FONT = new Font(FONT_FAMILY, Font.BOLD, 26);
    public static final Font SUBTITLE_FONT = new Font(FONT_FAMILY, Font.BOLD, 18);

    public static final Font HEADER_FONT = new Font(FONT_FAMILY, Font.BOLD, 28);

    public static final Font NORMAL_BTN_FONT = new Font(FONT_FAMILY, Font.BOLD, 14);
    public static final Font START_BTN_FONT = new Font(FONT_FAMILY, Font.BOLD, 18);

    private GUITypography() {}
}
