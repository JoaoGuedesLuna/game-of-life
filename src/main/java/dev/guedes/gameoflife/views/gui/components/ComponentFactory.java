package dev.guedes.gameoflife.views.gui.components;

import javax.swing.Box;
import javax.swing.JEditorPane;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import java.awt.Component;
import java.awt.Dimension;

import static dev.guedes.gameoflife.views.gui.styles.GUIColors.TEXT_PRIMARY;
import static dev.guedes.gameoflife.views.gui.styles.GUITypography.SUBTITLE_FONT;
import static dev.guedes.gameoflife.views.gui.styles.GUITypography.TITLE_FONT;

/**
 * Factory utility class responsible for creating pre-configured Swing UI components
 * used throughout the Game of Life graphical interface.
 *
 * @author João Guedes
 */
public class ComponentFactory {
    private ComponentFactory() {
        throw new UnsupportedOperationException("ComponentFactory is a utility class and cannot be instantiated.");
    }

    public static JLabel title(String text) {
        JLabel label = new JLabel(text);
        label.setFont(TITLE_FONT);
        label.setForeground(TEXT_PRIMARY);
        label.setAlignmentX(Component.LEFT_ALIGNMENT);
        return label;
    }

    public static JLabel subtitle(String text) {
        JLabel label = new JLabel(text);
        label.setFont(SUBTITLE_FONT);
        label.setForeground(TEXT_PRIMARY);
        label.setAlignmentX(Component.LEFT_ALIGNMENT);
        return label;
    }

    public static JEditorPane text(String html, int width) {
        JEditorPane pane = new JEditorPane();
        pane.setContentType("text/html");
        pane.setText(html);
        pane.setEditable(false);
        pane.setOpaque(false);
        pane.setAlignmentX(Component.LEFT_ALIGNMENT);
        pane.setBorder(new EmptyBorder(5, 0, 5, 0));
        pane.setMaximumSize(new Dimension(width, Integer.MAX_VALUE));
        pane.setPreferredSize(new Dimension(width, pane.getPreferredSize().height));
        return pane;
    }

    public static JScrollPane scroll(JPanel content, int horizontalScrollBarPolicy, int verticalScrollBarPolicy) {
        JScrollPane scroll = new JScrollPane(content);
        scroll.setBorder(null);
        scroll.getViewport().setBorder(null);
        scroll.setHorizontalScrollBarPolicy(horizontalScrollBarPolicy);
        scroll.setVerticalScrollBarPolicy(verticalScrollBarPolicy);
        scroll.getHorizontalScrollBar().setUnitIncrement(15);
        scroll.getVerticalScrollBar().setUnitIncrement(15);
        return scroll;
    }

    public static Component spacer(int height) { return Box.createRigidArea(new Dimension(0, height)); }
}
