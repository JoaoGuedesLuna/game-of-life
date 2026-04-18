package dev.guedes.gameoflife.utils.swing;

import org.junit.jupiter.api.Test;
import java.awt.Color;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;

/**
 * Unit tests for {@link HtmlUtils}.
 *
 * @author João Guedes
 */
class HtmlUtilsTest {
    @Test
    void constructor_ShouldThrowUnsupportedOperationException() throws NoSuchMethodException {
        Constructor<HtmlUtils> constructor = HtmlUtils.class.getDeclaredConstructor();
        constructor.setAccessible(true);

        InvocationTargetException ex = assertThrows(InvocationTargetException.class, constructor::newInstance);
        assertInstanceOf(UnsupportedOperationException.class, ex.getCause());
    }

    @Test
    void toHex_ShouldReturnCorrectHexValue_ForBlack() {
        Color color = new Color(0, 0, 0);

        String result = HtmlUtils.toHex(color);

        assertEquals("000000", result);
    }

    @Test
    void toHex_ShouldReturnCorrectHexValue_ForWhite() {
        Color color = new Color(255, 255, 255);

        String result = HtmlUtils.toHex(color);

        assertEquals("ffffff", result);
    }

    @Test
    void toHex_ShouldReturnCorrectHexValue_ForCustomColor() {
        Color color = new Color(255, 0, 128);

        String result = HtmlUtils.toHex(color);

        assertEquals("ff0080", result);
    }

    @Test
    void wrap_ShouldReturnFormattedHtmlString() {
        String body = "Hello World";
        Color color = new Color(255, 0, 0);
        String fontFamily = "Arial";

        String result = HtmlUtils.wrap(body, color, fontFamily);

        String expected =
                "<html>" +
                    "<body style='color:#ff0000; font-family:Arial; text-align: justify;'>" +
                        "Hello World" +
                    "</body>" +
                "</html>";

        assertEquals(expected, result);
    }

    @Test
    void wrap_ShouldIncludeGivenBodyContent() {
        String body = "Test Content";
        Color color = new Color(0, 255, 0);
        String fontFamily = "Verdana";

        String result = HtmlUtils.wrap(body, color, fontFamily);

        assertTrue(result.contains(body));
    }
}