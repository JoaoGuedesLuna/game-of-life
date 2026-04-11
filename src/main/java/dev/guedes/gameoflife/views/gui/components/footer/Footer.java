package dev.guedes.gameoflife.views.gui.components.footer;

import com.google.inject.Inject;
import dev.guedes.gameoflife.views.gui.components.buttons.Button;
import dev.guedes.gameoflife.views.gui.components.grid.GridPanel;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.Timer;
import javax.swing.border.EmptyBorder;
import java.awt.FlowLayout;

import static dev.guedes.gameoflife.views.gui.styles.GUIColors.BTN_ACTIVE_BG;
import static dev.guedes.gameoflife.views.gui.styles.GUIColors.BTN_ACTIVE_FG;
import static dev.guedes.gameoflife.views.gui.styles.GUIColors.BTN_DISABLED_BG;
import static dev.guedes.gameoflife.views.gui.styles.GUIColors.BTN_DISABLED_FG;
import static dev.guedes.gameoflife.views.gui.styles.GUIColors.FOOTER_BG;
import static dev.guedes.gameoflife.views.gui.styles.GUIDimensions.BTN_NORMAL_SIZE;
import static dev.guedes.gameoflife.views.gui.styles.GUIDimensions.BTN_START_SIZE;
import static dev.guedes.gameoflife.views.gui.styles.GUITypography.BTN_NORMAL_FONT;
import static dev.guedes.gameoflife.views.gui.styles.GUITypography.BTN_START_FONT;

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
        this.setBackground(FOOTER_BG);
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
        rulesBtn = new Button("Rules", BTN_NORMAL_SIZE, BTN_NORMAL_FONT);

        startStopBtn = new Button("Start", BTN_START_SIZE, BTN_START_FONT);
        startStopBtn.addActionListener(e -> handleStartPause());

        nextBtn = new Button("Next", BTN_NORMAL_SIZE, BTN_NORMAL_FONT);
        nextBtn.addActionListener(e -> handleNext());

        clearResetBtn = new Button("Clear", BTN_NORMAL_SIZE, BTN_NORMAL_FONT);
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
                btn.setBackground(BTN_DISABLED_BG);
                btn.setForeground(BTN_DISABLED_FG);
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
                btn.setBackground(BTN_ACTIVE_BG);
                btn.setForeground(BTN_ACTIVE_FG);
            } else {
                btn.setBackground(BTN_DISABLED_BG);
                btn.setForeground(BTN_DISABLED_FG);
            }
        }
    }
}