package dev.guedes.gameoflife.utils.swing;

import dev.guedes.gameoflife.exceptions.LookAndFeelException;
import org.junit.jupiter.api.Test;
import javax.swing.UIManager;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Test class for {@link LookAndFeelManager}.
 *
 * @author João Guedes
 */
class LookAndFeelManagerTest {
    @Test
    void constructor_ShouldThrowUnsupportedOperationException() {
        Constructor<?>[] constructors = LookAndFeelManager.class.getDeclaredConstructors();
        constructors[0].setAccessible(true);

        assertThrows(
                InvocationTargetException.class,
                () -> constructors[0].newInstance(),
                "Expected constructor to throw UnsupportedOperationException"
        );
    }

    @Test
    void setLookAndFeel_ShouldThrowLookAndFeelException_WhenLookAndFeelTypeIsNull() {
        assertThrows(
                LookAndFeelException.class,
                () -> LookAndFeelManager.setLookAndFeel(null),
                "Error configuring Look and Feel. "
        );
    }

    @Test
    void setLookAndFeel_ShouldNotThrow_WhenValidLookAndFeel() {
        assertDoesNotThrow(() -> LookAndFeelManager.setLookAndFeel(LookAndFeelType.METAL));
        assertEquals(LookAndFeelType.METAL.getClassName(), UIManager.getLookAndFeel().getClass().getName());
    }

    @Test
    void setLookAndFeel_ShouldThrowLookAndFeelException_WhenLookAndFeelClassNotFound() {
        String className = "non.existent.LookAndFeel";
        LookAndFeelType invalidType = mock(LookAndFeelType.class);

        when(invalidType.getClassName()).thenReturn(className);

        assertThrows(
                LookAndFeelException.class,
                () -> LookAndFeelManager.setLookAndFeel(invalidType),
                "Error configuring Look and Feel. Failed to apply Look and Feel: " + className
        );
    }
}
