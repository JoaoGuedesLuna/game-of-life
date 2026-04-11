package dev.guedes.gameoflife.views.gui.styles;

import java.awt.Dimension;

/**
 * Defines size and dimension constants used in the Swing GUI.
 * Centralizes layout-related values for consistency.
 * This includes button sizes, grid zoom limits, and spacing values.
 *
 * @author João Guedes
 */
public final class GUIDimensions {
    public static final Dimension BTN_NORMAL_SIZE = new Dimension(100, 40);
    public static final Dimension BTN_START_SIZE = new Dimension(140, 50);

    public static final int GRID_MIN_ZOOM = 8;
    public static final int GRID_MAX_ZOOM = 50;

    private GUIDimensions() {}
}