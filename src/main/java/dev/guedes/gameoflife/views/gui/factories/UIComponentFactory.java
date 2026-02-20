package dev.guedes.gameoflife.views.gui.factories;

import javax.swing.JButton;
import java.awt.Dimension;
import java.awt.Font;

import static dev.guedes.gameoflife.views.gui.constants.GUIStyle.BTN_ACTIVE_BG_COLOR;
import static dev.guedes.gameoflife.views.gui.constants.GUIStyle.BTN_ACTIVE_FG_COLOR;

/**
 * Utility class for creating and styling Swing components.
 *
 * @author João Guedes
 */
public final class UIComponentFactory {
    private UIComponentFactory() {
        throw new UnsupportedOperationException("UIComponentFactory is a utility class and cannot be instantiated.");
    }

    public static JButton createButton(String text, Dimension size, Font font) {
        JButton btn = new JButton(text);
        btn.setFocusPainted(false);
        btn.setFont(font);
        btn.setPreferredSize(size);
        btn.setBackground(BTN_ACTIVE_BG_COLOR);
        btn.setForeground(BTN_ACTIVE_FG_COLOR);
        return btn;
    }
}
