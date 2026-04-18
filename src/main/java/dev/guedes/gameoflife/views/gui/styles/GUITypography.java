package dev.guedes.gameoflife.views.gui.styles;

import java.awt.Font;

/**
 * Defines all font styles used in the Swing GUI.
 * Ensures consistent typography across the application.
 * This includes fonts for headers, buttons, and other UI components.
 *
 * @author João Guedes
 */
public final class GUITypography {
    public static final String FONT_FAMILY = "SansSerif";

    public static final Font TITLE_FONT = new Font(FONT_FAMILY, Font.BOLD, 26);
    public static final Font SUBTITLE_FONT = new Font(FONT_FAMILY, Font.BOLD, 18);

    public static final Font HEADER_FONT = new Font(FONT_FAMILY, Font.BOLD, 28);

    public static final Font BTN_NORMAL_FONT = new Font(FONT_FAMILY, Font.BOLD, 14);
    public static final Font BTN_START_FONT = new Font(FONT_FAMILY, Font.BOLD, 18);

    private GUITypography() {}
}
