package dev.guedes.gameoflife.views.gui.components.frame;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import java.awt.HeadlessException;
import java.net.URL;

/**
 * A reusable frame class for the application.
 *
 * @author João Guedes
 */
public class Frame extends JFrame {
    public Frame(String title, int width, int height, boolean maximized) throws HeadlessException {
        super(title);
        this.setIcon("/assets/icon.png");
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setSize(width, height);
        this.setLocationRelativeTo(null);

        if (maximized) this.setExtendedState(MAXIMIZED_BOTH);
    }

    private void setIcon(String iconPath) {
        URL url = getClass().getResource(iconPath);
        if (url == null) return;

        ImageIcon imageIcon = new ImageIcon(url);
        setIconImage(imageIcon.getImage());
    }
}
