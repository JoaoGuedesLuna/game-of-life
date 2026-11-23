package dev.guedes.gameoflife.utils.swing;

import org.junit.jupiter.api.Test;
import javax.swing.UIManager;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import dev.guedes.gameoflife.exceptions.LookAndFeelException;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/*
 * Copyright (c) 2026 João Guedes
 * Project : Game of Life
 * File : LookAndFeelManagerTest.java
 * Created on : 2026-11-09 17:00
 * Last modified: 2026-11-09 17:00
 *
 * Author : João Guedes
 * GitHub : https://github.com/JoaoGuedesLuna
 *
 * Copyright (c) 2026 João Guedes. All Rights Reserved.
 * Licensed under the MIT License.
 *
 * https://opensource.org/licenses/MIT
 */
class LookAndFeelManagerTest {
    @Test
    void constructor_ShouldThrowUnsupportedOperationException() throws NoSuchMethodException {
        Constructor<LookAndFeelManager> constructor = LookAndFeelManager.class.getDeclaredConstructor();
        constructor.setAccessible(true);

        InvocationTargetException ex = assertThrows(InvocationTargetException.class, constructor::newInstance);
        assertInstanceOf(UnsupportedOperationException.class, ex.getCause());
    }

    @Test
    void setLookAndFeel_ShouldThrowLookAndFeelException_WhenLookAndFeelTypeIsNull() {
        assertThrows(
                LookAndFeelException.class,
                () -> LookAndFeelManager.setLookAndFeel(null),
                "Error configuring Look and Feel. Look and Feel type cannot be null."
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

        LookAndFeelType mockLookAndFeelType = mock(LookAndFeelType.class);

        when(mockLookAndFeelType.getClassName()).thenReturn(className);

        assertThrows(
                LookAndFeelException.class,
                () -> LookAndFeelManager.setLookAndFeel(mockLookAndFeelType),
                "Error configuring Look and Feel. Failed to apply Look and Feel: " + className
        );
    }
}
