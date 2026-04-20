package dev.guedes.gameoflife.utils.swing;

import java.awt.Color;

/**
 * Utility class for generating simple HTML strings for Swing components.
 * Provides helper methods to wrap content with basic styling and to convert
 * {@link Color} instances into hexadecimal string representations.
 *
 * @author João Guedes
 */
public class HtmlUtils {
    private HtmlUtils() {
        throw new UnsupportedOperationException("HtmlUtils is a utility class and cannot be instantiated.");
    }

    public static String wrap(String body, Color color, String fontFamily) {
        return  "<html>" +
                    "<body style='color:#" + toHex(color) +
                    "; font-family:" + fontFamily +
                    "; text-align: justify;'>" +
                        body +
                    "</body>" +
                "</html>";
    }

    public static String toHex(Color color) {
        return String.format("%02x%02x%02x",
                color.getRed(),
                color.getGreen(),
                color.getBlue());
    }
}
