package dev.guedes.gameoflife.views.gui.components.footer;

import com.google.inject.Inject;
import dev.guedes.gameoflife.views.gui.components.grid.GridPanel;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.Timer;
import javax.swing.border.EmptyBorder;
import java.awt.FlowLayout;

import static dev.guedes.gameoflife.views.gui.constants.GUIStyle.*;
import static dev.guedes.gameoflife.views.gui.constants.GUIStyle.BTN_ACTIVE_FG_COLOR;
import static dev.guedes.gameoflife.views.gui.factories.UIComponentFactory.createButton;

/**
 * Footer panel containing game controls and rules shortcut.
 *
 * @author João Guedes
 */
public class Footer extends JPanel {
    private static final int DELAY = 200;

    private final GridPanel gridPanel;
    private JButton rulesBtn;
    private JButton startStopBtn;
    private JButton nextBtn;
    private JButton clearResetBtn;
    private Timer timer;
    private boolean startClicked = false;
    private boolean isRunning = false;

    @Inject
    public Footer(GridPanel gridPanel) {
        this.gridPanel = gridPanel;
        this.gridPanel.setOnGridChanged(this::updateButtonStates);

        this.setupLayout();
        this.setupTimer();
        this.initializeComponents();
    }

    private void setupLayout() {
        this.setBackground(FOOTER_BG_COLOR);
        this.setLayout(new FlowLayout(FlowLayout.CENTER, 15, 0));
        this.setBorder(new EmptyBorder(10, 20, 10, 20));
    }

    private void setupTimer() {
        timer = new Timer(DELAY, e -> {
            gridPanel.advanceGeneration();
            updateButtonStates();
        });
    }

    private void initializeComponents() {
        rulesBtn = createButton("Rules", BTN_SIZE_NORMAL, BTN_FONT_NORMAL);

        startStopBtn = createButton("Start", BTN_SIZE_START, BTN_FONT_START);
        startStopBtn.addActionListener(e -> handleStartPause());

        nextBtn = createButton("Next", BTN_SIZE_NORMAL, BTN_FONT_NORMAL);
        nextBtn.addActionListener(e -> handleNext());

        clearResetBtn = createButton("Clear", BTN_SIZE_NORMAL, BTN_FONT_NORMAL);
        clearResetBtn.addActionListener(e -> handleClearReset());

        this.add(rulesBtn);
        this.add(startStopBtn);
        this.add(nextBtn);
        this.add(clearResetBtn);

        updateButtonStates();
    }

    private void handleStartPause() {
        startClicked = true;
        isRunning = !isRunning;

        if (isRunning) {
            startStopBtn.setText("Stop");
            clearResetBtn.setText("Reset");
            gridPanel.save();
            timer.start();
        } else {
            startStopBtn.setText("Start");
            timer.stop();
        }
    }

    private void handleNext() {
        if (isRunning) return;

        if (!startClicked) {
            startClicked = true;
            clearResetBtn.setText("Reset");
            gridPanel.save();
        }

        gridPanel.advanceGeneration();
        updateButtonStates();
    }

    private void handleClearReset() {
        if (startClicked && isRunning) {
            gridPanel.reset();
        } else if (startClicked) {
            startClicked = false;
            clearResetBtn.setText("Clear");
            gridPanel.reset();
        } else {
            JButton[] buttons = { startStopBtn, nextBtn, clearResetBtn };

            for (JButton btn : buttons) {
                btn.setBackground(BTN_DISABLED_BG_COLOR);
                btn.setForeground(BTN_DISABLED_FG_COLOR);
            }
            gridPanel.clear();
        }
    }

    public void updateButtonStates() {
        boolean hasLife = gridPanel.hasLivingCells();

        if (!hasLife) {
            isRunning = false;
            startStopBtn.setText("Start");
            timer.stop();
        }

        JButton[] buttons = { startStopBtn, nextBtn, clearResetBtn };

        for (JButton btn : buttons) {
            if (hasLife) {
                btn.setBackground(BTN_ACTIVE_BG_COLOR);
                btn.setForeground(BTN_ACTIVE_FG_COLOR);
            } else {
                btn.setBackground(BTN_DISABLED_BG_COLOR);
                btn.setForeground(BTN_DISABLED_FG_COLOR);
            }
        }
    }
}