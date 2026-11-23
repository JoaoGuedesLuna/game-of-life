package dev.guedes.gameoflife.utils.swing;

import lombok.Getter;

/*
 * Copyright (c) 2026 João Guedes
 * Project : Game of Life
 * File : LookAndFeelType.java
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
@Getter
public enum LookAndFeelType {
    METAL("javax.swing.plaf.metal.MetalLookAndFeel"),
    MOTIF("com.sun.java.swing.plaf.motif.MotifLookAndFeel"),
    NIMBUS("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel"),
    WINDOWS("com.sun.java.swing.plaf.windows.WindowsLookAndFeel"),
    WINDOWS_CLASSIC("com.sun.java.swing.plaf.windows.WindowsClassicLookAndFeel");

    private final String className;

    LookAndFeelType(String className) { this.className = className; }
}
