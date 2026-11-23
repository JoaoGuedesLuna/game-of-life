package dev.guedes.gameoflife.views.gui.components.frame;

import javax.swing.JFrame;
import java.awt.HeadlessException;
import java.awt.Toolkit;

/*
 * Copyright (c) 2026 João Guedes
 * Project : Game of Life
 * File : Frame.java
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
public class Frame extends JFrame {
    public Frame(String title, int width, int height, boolean maximized, int closeOperation) throws HeadlessException {
        this.setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/assets/icon.png")));
        this.setTitle(title);
        this.setDefaultCloseOperation(closeOperation);
        this.setSize(width, height);
        this.setLocationRelativeTo(null);

        if (maximized) this.setExtendedState(MAXIMIZED_BOTH);
    }
}
