package dev.guedes.gameoflife.views.gui.components.header;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.BorderLayout;

import static dev.guedes.gameoflife.views.gui.constants.GUIStyle.APP_TITLE;
import static dev.guedes.gameoflife.views.gui.constants.GUIStyle.HEADER_BG_COLOR;
import static dev.guedes.gameoflife.views.gui.constants.GUIStyle.HEADER_FG_COLOR;
import static dev.guedes.gameoflife.views.gui.constants.GUIStyle.HEADER_FONT;

/**
 * Header panel for the application.
 * <p>
 * This component displays the application title with a specific blue background
 * and styled typography.
 * </p>
 *
 * @author João Guedes
 */
public class Header extends JPanel {
    public Header() { setupLayout(); addTitle(); }

    private void setupLayout() {
        this.setBackground(HEADER_BG_COLOR);
        this.setLayout(new BorderLayout());
        this.setBorder(new EmptyBorder(10, 20, 10, 20));
    }

    private void addTitle() {
        JLabel titleLabel = new JLabel(APP_TITLE);
        titleLabel.setForeground(HEADER_FG_COLOR);
        titleLabel.setFont(HEADER_FONT);

        this.add(titleLabel, BorderLayout.WEST);
    }
}
