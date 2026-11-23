package dev.guedes.gameoflife.utils.swing;

import java.awt.Dimension;
import java.awt.GraphicsEnvironment;
import java.awt.Toolkit;

/*
 * Copyright (c) 2026 João Guedes
 * Project : Game of Life
 * File : ScreenUtils.java
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
public final class ScreenUtils {
    private ScreenUtils() {
        throw new UnsupportedOperationException("ScreenUtils is a utility class and cannot be instantiated.");
    }

    public static int getScreenWidth() {
        if (GraphicsEnvironment.isHeadless()) return 0;

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

        return screenSize.width;
    }

    public static int getScreenHeight() {
        if (GraphicsEnvironment.isHeadless()) return 0;

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

        return screenSize.height;
    }
}
