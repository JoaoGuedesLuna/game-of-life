package dev.guedes.gameoflife.utils.swing;

import lombok.Getter;

/**
 * Enumeration representing the available Swing Look and Feel themes.
 * Provides the class names for each supported Look and Feel implementation.
 *
 * @author João Guedes
 */
@Getter
public enum LookAndFeelType {
    METAL("javax.swing.plaf.metal.MetalLookAndFeel"),
    NIMBUS("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel"),
    WINDOWS("com.sun.java.swing.plaf.windows.WindowsLookAndFeel"),
    WINDOWS_CLASSIC("com.sun.java.swing.plaf.windows.WindowsClassicLookAndFeel"),
    MOTIF("com.sun.java.swing.plaf.motif.MotifLookAndFeel");

    private final String className;

    LookAndFeelType(String className) { this.className = className; }
}
