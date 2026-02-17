package dev.guedes.gameoflife.views.gui.constants;

import java.awt.Color;
import java.awt.Font;

/**
 * Centralized constants for the Swing interface.
 * Separated into Metadata, Colors, and Typography.
 *
 * @author João Guedes
 */
public final class GUIStyle {
    private GUIStyle() {}

    public static final String APP_TITLE = "Conway’s Game of Life";
    public static final int APP_DEFAULT_WIDTH = 1000;
    public static final int APP_DEFAULT_HEIGHT = 650;

    public static final Color COLOR_HEADER_BG = new Color(51, 102, 187);
    public static final Color COLOR_HEADER_FG = Color.WHITE;

    public static final Font FONT_HEADER = new Font("SansSerif", Font.PLAIN, 24);
}