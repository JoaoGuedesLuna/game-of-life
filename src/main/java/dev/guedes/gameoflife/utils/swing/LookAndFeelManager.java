package dev.guedes.gameoflife.utils.swing;

import dev.guedes.gameoflife.exceptions.LookAndFeelException;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import java.util.Objects;

/**
 * Utility class for managing Swing Look and Feel configurations.
 * Provides methods to set and manage the application's visual appearance.
 *
 * @author João Guedes
 */
public final class LookAndFeelManager {
    private LookAndFeelManager() {
        throw new UnsupportedOperationException("SwingLookAndFeelManager is a utility class and cannot be instantiated.");
    }

    public static void setLookAndFeel(LookAndFeelType lookAndFeelType) {
        try {
            Objects.requireNonNull(lookAndFeelType);
            applyLookAndFeel(lookAndFeelType);
        } catch (NullPointerException | LookAndFeelException  e) {
            String message = e.getMessage() != null ? e.getMessage() : "";
            throw new LookAndFeelException("Error configuring Look and Feel. " + message);
        }
    }

    private static void applyLookAndFeel(LookAndFeelType lookAndFeelType) {
        try {
            UIManager.setLookAndFeel(lookAndFeelType.getClassName());
        } catch (ClassNotFoundException |
                 InstantiationException |
                 IllegalAccessException |
                 UnsupportedLookAndFeelException e
        ) {
            throw new LookAndFeelException("Failed to apply Look and Feel: " + lookAndFeelType.getClassName());
        }
    }
}
