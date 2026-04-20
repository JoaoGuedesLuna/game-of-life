package dev.guedes.gameoflife.utils.swing;

import javax.swing.JEditorPane;
import javax.swing.event.HyperlinkEvent;
import java.awt.Cursor;
import java.awt.Desktop;
import java.net.URI;

/**
 * Utility class for handling hyperlinks in {@link JEditorPane} components.
 *
 * @author João Guedes
 */
public class LinkUtils {
    private LinkUtils() {
        throw new UnsupportedOperationException("LinkUtils is a utility class and cannot be instantiated.");
    }

    public static void apply(JEditorPane pane) {
        pane.addHyperlinkListener(e -> {
            if (HyperlinkEvent.EventType.ACTIVATED.equals(e.getEventType())) {
                open(e.getURL().toString());
            } else if (HyperlinkEvent.EventType.ENTERED.equals(e.getEventType())) {
                pane.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            } else if (HyperlinkEvent.EventType.EXITED.equals(e.getEventType())) {
                pane.setCursor(Cursor.getDefaultCursor());
            }
        });
    }

    private static void open(String url) {
        try {
            Desktop.getDesktop().browse(new URI(url));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
