package dev.guedes.gameoflife.utils.swing;

import org.junit.jupiter.api.Test;
import java.awt.Dimension;
import java.awt.GraphicsEnvironment;
import java.awt.Toolkit;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assumptions.assumeFalse;

/**
 * Test class for {@link ScreenUtils}.
 *
 * @author João Guedes
 */
class ScreenUtilsTest {
    @Test
    void constructor_ShouldThrowUnsupportedOperationException() throws NoSuchMethodException {
        Constructor<ScreenUtils> constructor = ScreenUtils.class.getDeclaredConstructor();
        constructor.setAccessible(true);

        InvocationTargetException ex = assertThrows(InvocationTargetException.class, constructor::newInstance);
        assertInstanceOf(UnsupportedOperationException.class, ex.getCause());
    }

    @Test
    void getScreenWidth_ShouldReturnPositiveValue() {
        assumeFalse(GraphicsEnvironment.isHeadless());

        int width = ScreenUtils.getScreenWidth();

        assertTrue(width > 0, "Screen width should be greater than zero");
    }

    @Test
    void getScreenHeight_ShouldReturnPositiveValue() {
        assumeFalse(GraphicsEnvironment.isHeadless());

        int height = ScreenUtils.getScreenHeight();

        assertTrue(height > 0, "Screen height should be greater than zero");
    }

    @Test
    void getScreenWidthAndHeight_ShouldMatchToolkitValues() {
        assumeFalse(GraphicsEnvironment.isHeadless());

        Dimension expected = Toolkit.getDefaultToolkit().getScreenSize();

        assertEquals(expected.width, ScreenUtils.getScreenWidth());
        assertEquals(expected.height, ScreenUtils.getScreenHeight());
    }
}
