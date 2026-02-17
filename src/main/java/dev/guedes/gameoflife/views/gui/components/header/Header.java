package dev.guedes.gameoflife.views.gui.components.header;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.BorderLayout;

import static dev.guedes.gameoflife.views.gui.constants.GUIStyle.APP_TITLE;
import static dev.guedes.gameoflife.views.gui.constants.GUIStyle.COLOR_HEADER_BG;
import static dev.guedes.gameoflife.views.gui.constants.GUIStyle.COLOR_HEADER_FG;
import static dev.guedes.gameoflife.views.gui.constants.GUIStyle.FONT_HEADER;

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
        this.setBackground(COLOR_HEADER_BG);
        this.setLayout(new BorderLayout());
        this.setBorder(new EmptyBorder(10, 20, 10, 20));
    }

    private void addTitle() {
        JLabel titleLabel = new JLabel(APP_TITLE);
        titleLabel.setForeground(COLOR_HEADER_FG);
        titleLabel.setFont(FONT_HEADER);

        this.add(titleLabel, BorderLayout.WEST);
    }
}
