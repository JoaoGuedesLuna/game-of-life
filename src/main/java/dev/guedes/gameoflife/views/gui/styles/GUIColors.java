package dev.guedes.gameoflife.views.gui.styles;

import java.awt.Color;

/*
 * Copyright (c) 2026 João Guedes
 * Project : Game of Life
 * File : GUIColors.java
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
public final class GUIColors {
    public static final Color PRIMARY_LIGHT = new Color(187, 231, 233);
    public static final Color PRIMARY = new Color(29, 77, 91);
    public static final Color PRIMARY_DARK = new Color(22, 41, 46);

    public static final Color SECONDARY_LIGHT = new Color(153, 153, 153);
    public static final Color SECONDARY = new Color(78, 78, 78);
    public static final Color SECONDARY_DARK = new Color(61, 61, 61);

    public static final Color ACCENT = new Color(255, 255, 0);

    public static final Color NEUTRAL_LIGHT = new Color(234, 244, 245);
    public static final Color NEUTRAL = new Color(93, 108, 112);
    public static final Color NEUTRAL_DARK = new Color(39, 54, 60);

    public static final Color TEXT_PRIMARY = NEUTRAL_LIGHT;

    public static final Color HEADER_BG = PRIMARY_DARK;
    public static final Color HEADER_FG = PRIMARY_LIGHT;

    public static final Color GRID_BG = SECONDARY_DARK;
    public static final Color GRID_LINE_COLOR = SECONDARY;
    public static final Color GRID_ALIVE_COLOR = ACCENT;

    public static final Color FOOTER_BG = PRIMARY_DARK;

    public static final Color EXPLANATION_BG = SECONDARY_DARK;
    public static final Color EXPLANATION_FG = TEXT_PRIMARY;
    public static final Color EXPLANATION_CARD_BG = SECONDARY_DARK;

    public static final Color START_BTN_ACTIVE_BG = new Color(245, 219, 61);
    public static final Color START_BTN_ACTIVE_FG = new Color(14, 42, 51);

    public static final Color BTN_ACTIVE_BG = PRIMARY;
    public static final Color BTN_ACTIVE_FG = TEXT_PRIMARY;

    public static final Color BTN_DISABLED_BG = NEUTRAL_DARK;
    public static final Color BTN_DISABLED_FG = NEUTRAL;

    private GUIColors() {}
}
