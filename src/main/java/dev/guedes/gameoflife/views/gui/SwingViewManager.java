package dev.guedes.gameoflife.views.gui;

import com.google.inject.Inject;
import javax.swing.SwingUtilities;
import java.util.logging.Logger;

import dev.guedes.gameoflife.utils.swing.LookAndFeelManager;
import dev.guedes.gameoflife.utils.swing.LookAndFeelType;
import dev.guedes.gameoflife.views.ViewManager;

/*
 * Copyright (c) 2026 João Guedes
 * Project : Game of Life
 * File : SwingViewManager.java
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
public class SwingViewManager implements ViewManager {
    private static final Logger logger = Logger.getLogger(SwingViewManager.class.getName());

    private final GUIMainView guiMainView;

    @Inject
    public SwingViewManager(GUIMainView guiMainView) { this.guiMainView = guiMainView; }

    public void start() {
        configureLookAndFeel();
        displayMainView();
    }

    private static void configureLookAndFeel() {
        try {
            LookAndFeelManager.setLookAndFeel(LookAndFeelType.WINDOWS);
        } catch (Exception e) {
            logger.info(e.getMessage());
        }
    }

    private void displayMainView() { SwingUtilities.invokeLater(guiMainView::display); }
}
