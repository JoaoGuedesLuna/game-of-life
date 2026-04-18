package dev.guedes.gameoflife.views.gui.components.footer;

import com.google.inject.Inject;
import dev.guedes.gameoflife.views.gui.GUIExplanationView;
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
import static dev.guedes.gameoflife.views.gui.styles.GUIDimensions.BTN_EXPLANATION_SIZE;
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
    private static final String BTN_EXPLANATION_TEXT = "Explanation";
    private static final String BTN_START_TEXT = "Start";
    private static final String BTN_STOP_TEXT = "Stop";
    private static final String BTN_NEXT_TEXT = "Next";
    private static final String BTN_CLEAR_TEXT = "Clear";
    private static final String BTN_RESET_TEXT = "Reset";
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
        this.gridPanel.setOnGridChanged(this::updateButtonStates);

        this.explanationView = explanationView;

        this.setupLayout();
        this.setupTimer();
        this.initComponents();
    }

    private void updateButtonStates() {
        boolean hasLivingCells = gridPanel.hasLivingCells();

        if (!hasLivingCells) {
            timer.stop();
            isRunning = false;
            startStopBtn.setText(BTN_START_TEXT);
            clearResetBtn.setText(BTN_CLEAR_TEXT);
            gridPanel.save();
        }

        JButton[] buttons = { startStopBtn, nextBtn, clearResetBtn };

        for (JButton btn : buttons) {
            if (hasLivingCells) {
                btn.setBackground(BTN_ACTIVE_BG);
                btn.setForeground(BTN_ACTIVE_FG);
            } else {
                btn.setBackground(BTN_DISABLED_BG);
                btn.setForeground(BTN_DISABLED_FG);
            }
        }
    }

    private void setupLayout() {
        setLayout(new FlowLayout(FlowLayout.CENTER, 15, 0));
        setBorder(new EmptyBorder(10, 20, 10, 20));
        setBackground(FOOTER_BG);
    }

    private void setupTimer() {
        timer = new Timer(DELAY, e -> gridPanel.advanceGeneration());
    }

    private void initComponents() {
        explanationBtn = new Button(BTN_EXPLANATION_TEXT, BTN_EXPLANATION_SIZE, BTN_NORMAL_FONT);
        explanationBtn.addActionListener(e -> handleExplanation());

        startStopBtn = new Button(BTN_START_TEXT, BTN_START_SIZE, BTN_START_FONT);
        startStopBtn.setBackground(BTN_DISABLED_BG);
        startStopBtn.setForeground(BTN_DISABLED_FG);
        startStopBtn.addActionListener(e -> handleStartStop());

        nextBtn = new Button(BTN_NEXT_TEXT, BTN_NORMAL_SIZE, BTN_NORMAL_FONT);
        nextBtn.setBackground(BTN_DISABLED_BG);
        nextBtn.setForeground(BTN_DISABLED_FG);
        nextBtn.addActionListener(e -> handleNext());

        clearResetBtn = new Button(BTN_CLEAR_TEXT, BTN_NORMAL_SIZE, BTN_NORMAL_FONT);
        clearResetBtn.setBackground(BTN_DISABLED_BG);
        clearResetBtn.setForeground(BTN_DISABLED_FG);
        clearResetBtn.addActionListener(e -> handleClearReset());

        add(explanationBtn);
        add(startStopBtn);
        add(nextBtn);
        add(clearResetBtn);
    }

    private void handleExplanation() { explanationView.display(); }

    private void handleStartStop() {
        if (!gridPanel.hasLivingCells()) return;

        isRunning = !isRunning;

        if (!isRunning) {
            startStopBtn.setText(BTN_START_TEXT);
            timer.stop();
            return;
        }

        if (!startNextClicked) {
            startNextClicked = true;
            gridPanel.save();
        }

        startStopBtn.setText(BTN_STOP_TEXT);
        clearResetBtn.setText(BTN_RESET_TEXT);
        timer.start();
    }

    private void handleNext() {
        if (isRunning) return;

        if (!gridPanel.hasLivingCells()) return;

        if (!startNextClicked) {
            startNextClicked = true;
            clearResetBtn.setText(BTN_RESET_TEXT);
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
            clearResetBtn.setText(BTN_CLEAR_TEXT);
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
