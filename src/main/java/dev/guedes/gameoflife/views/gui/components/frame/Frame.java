package dev.guedes.gameoflife.views.gui.components.frame;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.WindowConstants;
import java.awt.HeadlessException;
import java.net.URL;

/**
 * A reusable frame class for the application.
 *
 * @author João Guedes
 */
public class Frame extends JFrame {
    public Frame(String title, int width, int height) throws HeadlessException {
        super(title);
        this.setIcon("/assets/icon.png");
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setSize(width, height);
        this.setLocationRelativeTo(null);
    }

    private void setIcon(String iconPath) {
        URL url = getClass().getResource(iconPath);
        if (url == null) return;

        ImageIcon imageIcon = new ImageIcon(url);
        setIconImage(imageIcon.getImage());
    }
}
