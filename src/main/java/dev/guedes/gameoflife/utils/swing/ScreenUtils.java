package dev.guedes.gameoflife.utils.swing;

import java.awt.Dimension;
import java.awt.Toolkit;

/**
 * Utility class for screen-related operations.
 *
 * @author João Guedes
 */
public final class ScreenUtils {
    private ScreenUtils() {
        throw new UnsupportedOperationException("ScreenUtils is a utility class and cannot be instantiated.");
    }

    public static int getScreenWidth() {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        return screenSize.width;
    }

    public static int getScreenHeight() {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        return screenSize.height;
    }

}
