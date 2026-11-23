package dev.guedes.gameoflife.views.gui.components.footer;

import com.google.inject.Inject;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.Timer;
import javax.swing.border.EmptyBorder;
import java.awt.FlowLayout;

import dev.guedes.gameoflife.views.gui.GUIExplanationView;
import dev.guedes.gameoflife.views.gui.components.buttons.Button;
import dev.guedes.gameoflife.views.gui.components.grid.GridPanel;

import static dev.guedes.gameoflife.views.gui.styles.GUIColors.BTN_ACTIVE_BG;
import static dev.guedes.gameoflife.views.gui.styles.GUIColors.BTN_ACTIVE_FG;
import static dev.guedes.gameoflife.views.gui.styles.GUIColors.BTN_DISABLED_BG;
import static dev.guedes.gameoflife.views.gui.styles.GUIColors.BTN_DISABLED_FG;
import static dev.guedes.gameoflife.views.gui.styles.GUIColors.FOOTER_BG;
import static dev.guedes.gameoflife.views.gui.styles.GUIColors.START_BTN_ACTIVE_BG;
import static dev.guedes.gameoflife.views.gui.styles.GUIColors.START_BTN_ACTIVE_FG;
import static dev.guedes.gameoflife.views.gui.styles.GUIDimensions.EXPLANATION_BTN_SIZE;
import static dev.guedes.gameoflife.views.gui.styles.GUIDimensions.NORMAL_BTN_SIZE;
import static dev.guedes.gameoflife.views.gui.styles.GUIDimensions.START_BTN_SIZE;
import static dev.guedes.gameoflife.views.gui.styles.GUITypography.NORMAL_BTN_FONT;
import static dev.guedes.gameoflife.views.gui.styles.GUITypography.START_BTN_FONT;

/*
 * Copyright (c) 2026 João Guedes
 * Project : Game of Life
 * File : Footer.java
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
public class Footer extends JPanel {
    private static final String EXPLANATION_BTN_TEXT = "Explanation";
    private static final String START_BTN_TEXT = "Start";
    private static final String STOP_BTN_TEXT = "Stop";
    private static final String NEXT_BTN_TEXT = "Next";
    private static final String CLEAR_BTN_TEXT = "Clear";
    private static final String RESET_BTN_TEXT = "Reset";
    private static final int DELAY = 200;

    private final GridPanel gridPanel;
    private final GUIExplanationView explanationView;
    private JButton explanationBtn;
    private JButton startStopBtn;
    private JButton nextBtn;
    private JButton clearResetBtn;
    private Timer timer;
    private boolean startNextClicked = false;
    private boolean isRunning = false;

    @Inject
    public Footer(GridPanel gridPanel, GUIExplanationView explanationView) {
        this.gridPanel = gridPanel;
        this.explanationView = explanationView;

        this.gridPanel.setOnGridChanged(this::updateButtonStates);

        this.setupTimer();
        this.initComponents();
    }

    private void setupTimer() { timer = new Timer(DELAY, e -> gridPanel.advanceGeneration()); }

    private void initComponents() {
        setLayout(new FlowLayout(FlowLayout.CENTER, 15, 0));
        setBorder(new EmptyBorder(10, 20, 10, 20));
        setBackground(FOOTER_BG);

        explanationBtn = new Button(EXPLANATION_BTN_TEXT, EXPLANATION_BTN_SIZE, NORMAL_BTN_FONT, BTN_ACTIVE_BG, BTN_ACTIVE_FG);
        explanationBtn.addActionListener(e -> handleExplanation());

        startStopBtn = new Button(START_BTN_TEXT, START_BTN_SIZE, START_BTN_FONT, BTN_DISABLED_BG, BTN_DISABLED_FG);
        startStopBtn.addActionListener(e -> handleStartStop());

        nextBtn = new Button(NEXT_BTN_TEXT, NORMAL_BTN_SIZE, NORMAL_BTN_FONT, BTN_DISABLED_BG, BTN_DISABLED_FG);
        nextBtn.addActionListener(e -> handleNext());

        clearResetBtn = new Button(CLEAR_BTN_TEXT, NORMAL_BTN_SIZE, NORMAL_BTN_FONT, BTN_DISABLED_BG, BTN_DISABLED_FG);
        clearResetBtn.addActionListener(e -> handleClearReset());

        add(explanationBtn);
        add(startStopBtn);
        add(nextBtn);
        add(clearResetBtn);
    }

    private void updateButtonStates() {
        boolean hasLivingCells = gridPanel.hasLivingCells();

        if (!hasLivingCells) {
            timer.stop();
            isRunning = startNextClicked = false;

            startStopBtn.setText(START_BTN_TEXT);
            startStopBtn.setBackground(BTN_DISABLED_BG);
            startStopBtn.setForeground(BTN_DISABLED_FG);

            clearResetBtn.setText(CLEAR_BTN_TEXT);
            clearResetBtn.setBackground(BTN_DISABLED_BG);
            clearResetBtn.setForeground(BTN_DISABLED_FG);

            nextBtn.setBackground(BTN_DISABLED_BG);
            nextBtn.setForeground(BTN_DISABLED_FG);

            gridPanel.save();
        } else {
            startStopBtn.setBackground(START_BTN_ACTIVE_BG);
            startStopBtn.setForeground(START_BTN_ACTIVE_FG);

            nextBtn.setBackground(BTN_ACTIVE_BG);
            nextBtn.setForeground(BTN_ACTIVE_FG);

            clearResetBtn.setBackground(BTN_ACTIVE_BG);
            clearResetBtn.setForeground(BTN_ACTIVE_FG);
        }
    }

    private void handleExplanation() { explanationView.display(); }

    private void handleStartStop() {
        if (!gridPanel.hasLivingCells()) return;

        isRunning = !isRunning;

        if (!isRunning) {
            timer.stop();
            startStopBtn.setText(START_BTN_TEXT);
            return;
        }

        if (!startNextClicked) {
            startNextClicked = true;
            gridPanel.save();
        }

        startStopBtn.setText(STOP_BTN_TEXT);
        clearResetBtn.setText(RESET_BTN_TEXT);

        timer.start();
    }

    private void handleNext() {
        if (isRunning || !gridPanel.hasLivingCells()) return;

        if (!startNextClicked) {
            startNextClicked = true;
            clearResetBtn.setText(RESET_BTN_TEXT);
            gridPanel.save();
        }

        gridPanel.advanceGeneration();
    }

    private void handleClearReset() {
        if (!gridPanel.hasLivingCells()) return;

        if (isRunning) {
            gridPanel.reset();
            return;
        }

        if (startNextClicked) {
            startNextClicked = false;
            clearResetBtn.setText(CLEAR_BTN_TEXT);
            gridPanel.reset();
            return;
        }

        JButton[] buttons = { startStopBtn, nextBtn, clearResetBtn };

        for (JButton btn : buttons) {
            btn.setBackground(BTN_DISABLED_BG);
            btn.setForeground(BTN_DISABLED_FG);
        }

        gridPanel.clear();
    }
}
