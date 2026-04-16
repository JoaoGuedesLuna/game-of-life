package dev.guedes.gameoflife.views.gui.styles;

import java.awt.Color;

/**
 * Defines all color constants used in the Swing GUI.
 * Centralizes the color palette for consistency and easy maintenance.
 * This includes colors for headers, grid, buttons, and general UI elements.
 *
 * @author João Guedes
 */
public final class GUIColors {
    public static final Color PRIMARY_LIGHT = new Color(180, 200, 231);
    public static final Color PRIMARY = new Color(54, 99, 168);
    public static final Color PRIMARY_DARK = new Color(42, 78, 151);

    public static final Color SECONDARY_LIGHT = new Color(153, 153, 153);
    public static final Color SECONDARY = new Color(126, 126, 126);
    public static final Color SECONDARY_DARK = new Color(100, 100, 100);

    public static final Color ACCENT = new Color(255, 255, 0);

    public static final Color NEUTRAL_LIGHT = new Color(204, 204, 204);
    public static final Color NEUTRAL = new Color(153, 153, 153);
    public static final Color NEUTRAL_DARK = new Color(55, 55, 55);

    public static final Color TEXT_PRIMARY = Color.WHITE;

    public static final Color HEADER_BG = PRIMARY;
    public static final Color HEADER_FG = PRIMARY_LIGHT;

    public static final Color GRID_BG = SECONDARY;
    public static final Color GRID_LINE_COLOR = SECONDARY_LIGHT;
    public static final Color GRID_ALIVE_COLOR = ACCENT;

    public static final Color FOOTER_BG = SECONDARY_DARK;

    public static final Color EXPLANATION_BG = SECONDARY_DARK;
    public static final Color EXPLANATION_FG = TEXT_PRIMARY;
    public static final Color EXPLANATION_CARD_BG = NEUTRAL_DARK;

    public static final Color BTN_ACTIVE_BG = PRIMARY_DARK;
    public static final Color BTN_ACTIVE_FG = TEXT_PRIMARY;

    public static final Color BTN_DISABLED_BG = NEUTRAL;
    public static final Color BTN_DISABLED_FG = NEUTRAL_LIGHT;

    private GUIColors() {}
}
