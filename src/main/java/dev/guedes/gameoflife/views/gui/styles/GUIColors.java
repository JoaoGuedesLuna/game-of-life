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
    public static final Color HEADER_BG = new Color(54, 99, 168);
    public static final Color HEADER_FG = new Color(180, 200, 231);

    public static final Color GRID_BG = new Color(126, 126, 126);
    public static final Color GRID_LINE_COLOR = new Color(153, 153, 153);
    public static final Color GRID_ALIVE_COLOR = new Color(200, 200, 200);

    public static final Color FOOTER_BG = new Color(100, 100, 100);

    public static final Color BTN_ACTIVE_BG = new Color(42, 78, 151);
    public static final Color BTN_ACTIVE_FG = Color.WHITE;

    public static final Color BTN_DISABLED_BG = new Color(153, 153, 153);
    public static final Color BTN_DISABLED_FG = new Color(204, 204, 204);

    private GUIColors() {}
}
