package dev.guedes.gameoflife.views.gui.components.header;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.BorderLayout;

import static dev.guedes.gameoflife.views.gui.styles.GUIColors.HEADER_BG;
import static dev.guedes.gameoflife.views.gui.styles.GUIColors.HEADER_FG;
import static dev.guedes.gameoflife.views.gui.styles.GUIMetadata.APP_TITLE;
import static dev.guedes.gameoflife.views.gui.styles.GUITypography.HEADER_FONT;

/*
 * Copyright (c) 2026 João Guedes
 * Project : Game of Life
 * File : Header.java
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
public class Header extends JPanel {
    public Header() {
        this.setLayout(new BorderLayout());
        this.setBorder(new EmptyBorder(10, 20, 10, 20));
        this.setBackground(HEADER_BG);
        this.setTitle(APP_TITLE);
    }

    private void setTitle(String title) {
        JLabel titleLabel = new JLabel(title);

        titleLabel.setForeground(HEADER_FG);
        titleLabel.setFont(HEADER_FONT);

        add(titleLabel, BorderLayout.WEST);
    }
}
