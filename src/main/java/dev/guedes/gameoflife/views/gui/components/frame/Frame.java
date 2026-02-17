package dev.guedes.gameoflife.views.gui.components.frame;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.WindowConstants;
import java.awt.HeadlessException;
import java.util.Optional;

/**
 * A reusable frame class for the application.
 *
 * @author João Guedes
 */
public class Frame extends JFrame {
    public Frame(String title, int width, int height) throws HeadlessException {
        super(title);
        setupDefaultSettings(width, height);
        loadIcon("/assets/icon.png");
    }

    private void setupDefaultSettings(int width, int height) {
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setSize(width, height);
        setLocationRelativeTo(null);
    }

    private void loadIcon(String path) {
        Optional.ofNullable(getClass().getResource(path))
                .map(ImageIcon::new)
                .map(ImageIcon::getImage)
                .ifPresent(this::setIconImage);
    }
}
