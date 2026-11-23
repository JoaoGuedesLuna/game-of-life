package dev.guedes.gameoflife.views.gui.components.buttons;

import javax.swing.JButton;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;

/*
 * Copyright (c) 2026 João Guedes
 * Project : Game of Life
 * File : Button.java
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
public class Button extends JButton {
    public Button(String text, Dimension size, Font font, Color backgroundColor, Color foregroundColor) {
        this.setText(text);
        this.setFont(font);
        this.setBackground(backgroundColor);
        this.setForeground(foregroundColor);
        this.setPreferredSize(size);
        this.setCursor(new Cursor(Cursor.HAND_CURSOR));
        this.setFocusPainted(false);
        this.setBorderPainted(false);
    }
}
