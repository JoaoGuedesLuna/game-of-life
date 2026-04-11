package dev.guedes.gameoflife.views.gui.components.buttons;

import javax.swing.JButton;
import java.awt.Dimension;
import java.awt.Font;

import static dev.guedes.gameoflife.views.gui.styles.GUIColors.BTN_ACTIVE_BG;
import static dev.guedes.gameoflife.views.gui.styles.GUIColors.BTN_ACTIVE_FG;

/**
 * Reusable component button.
 *
 * @author João Guedes
 */
public class Button extends JButton {
    public Button(String text, Dimension size, Font font) {
        this.setText(text);
        this.setFont(font);

        this.setBackground(BTN_ACTIVE_BG);
        this.setForeground(BTN_ACTIVE_FG);

        this.setPreferredSize(size);

        this.setFocusPainted(false);
        this.setBorderPainted(false);

        this.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mousePressed(java.awt.event.MouseEvent e) {
                if (Button.this.isEnabled()) Button.this.setBorderPainted(true);
            }

            @Override
            public void mouseReleased(java.awt.event.MouseEvent e) {
                Button.this.setBorderPainted(false);
            }
        });
    }
}
