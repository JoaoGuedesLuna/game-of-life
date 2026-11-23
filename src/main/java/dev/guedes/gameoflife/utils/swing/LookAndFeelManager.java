package dev.guedes.gameoflife.utils.swing;

import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import java.util.Objects;

import dev.guedes.gameoflife.exceptions.LookAndFeelException;

/*
 * Copyright (c) 2026 João Guedes
 * Project : Game of Life
 * File : LookAndFeelManager.java
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
public final class LookAndFeelManager {
    private LookAndFeelManager() {
        throw new UnsupportedOperationException("LookAndFeelManager is a utility class and cannot be instantiated.");
    }

    public static void setLookAndFeel(LookAndFeelType lookAndFeelType) {
        try {
            Objects.requireNonNull(lookAndFeelType, "Look and Feel type cannot be null.");
            applyLookAndFeel(lookAndFeelType);
        } catch (NullPointerException | LookAndFeelException  e) {
            String message = String.format(
                    "Error configuring Look and Feel. %s",
                    Objects.toString(e.getMessage(), "An unexpected error occurred.")
            );
            throw new LookAndFeelException(message);
        }
    }

    private static void applyLookAndFeel(LookAndFeelType lookAndFeelType) {
        try {
            UIManager.setLookAndFeel(lookAndFeelType.getClassName());
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException e) {
            throw new LookAndFeelException("Failed to apply Look and Feel: " + lookAndFeelType.getClassName());
        }
    }
}
