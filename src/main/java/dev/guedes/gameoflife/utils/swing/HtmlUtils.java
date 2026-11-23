package dev.guedes.gameoflife.utils.swing;

import java.awt.Color;

/*
 * Copyright (c) 2026 João Guedes
 * Project : Game of Life
 * File : HtmlUtils.java
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
public final class HtmlUtils {
    private HtmlUtils() {
        throw new UnsupportedOperationException("HtmlUtils is a utility class and cannot be instantiated.");
    }

    public static String wrap(String body, Color color, String fontFamily) {
        return  "<html>" +
                    "<body style='color:#" + toHex(color) +
                    "; font-family:" + fontFamily +
                    "; text-align: justify;'>" +
                        body +
                    "</body>" +
                "</html>";
    }

    public static String toHex(Color color) {
        return String.format("%02x%02x%02x",
                color.getRed(),
                color.getGreen(),
                color.getBlue()
        );
    }
}
