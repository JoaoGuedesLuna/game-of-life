package dev.guedes.gameoflife.views.gui.factories;

import javax.swing.JButton;
import java.awt.Dimension;
import java.awt.Font;

import static dev.guedes.gameoflife.views.gui.styles.GUIColors.BTN_ACTIVE_BG;
import static dev.guedes.gameoflife.views.gui.styles.GUIColors.BTN_ACTIVE_FG;

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
        btn.setBackground(BTN_ACTIVE_BG);
        btn.setForeground(BTN_ACTIVE_FG);
        btn.setFocusPainted(false);
        btn.setBorderPainted(false);
        btn.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mousePressed(java.awt.event.MouseEvent e) {
                if (btn.isEnabled()) {
                    btn.setBorderPainted(true);
                }
            }

            @Override
            public void mouseReleased(java.awt.event.MouseEvent e) {
                btn.setBorderPainted(false);
            }
        });
        return btn;
    }
}
