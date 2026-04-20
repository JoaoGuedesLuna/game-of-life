package dev.guedes.gameoflife.utils.swing;

import org.junit.jupiter.api.Test;
import javax.swing.JEditorPane;
import javax.swing.event.HyperlinkListener;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * Unit tests for {@link LinkUtils}.
 *
 * @author João Guedes
 */
class LinkUtilsTest {
    @Test
    void constructor_ShouldThrowUnsupportedOperationException() throws NoSuchMethodException {
        Constructor<LinkUtils> constructor = LinkUtils.class.getDeclaredConstructor();
        constructor.setAccessible(true);

        InvocationTargetException ex = assertThrows(InvocationTargetException.class, constructor::newInstance);
        assertInstanceOf(UnsupportedOperationException.class, ex.getCause());
    }

    @Test
    void apply_ShouldAddHyperlinkListener() {
        JEditorPane pane = new JEditorPane();

        LinkUtils.apply(pane);

        HyperlinkListener[] listeners = pane.getHyperlinkListeners();

        assertTrue(listeners.length > 0);
    }

    @Test
    void apply_ShouldNotThrowException_WhenCalled() {
        JEditorPane pane = new JEditorPane();

        assertDoesNotThrow(() -> LinkUtils.apply(pane));
    }
}
