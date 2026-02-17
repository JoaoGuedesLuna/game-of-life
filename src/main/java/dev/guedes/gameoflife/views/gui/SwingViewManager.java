package dev.guedes.gameoflife.views.gui;

import com.google.inject.Inject;
import dev.guedes.gameoflife.utils.swing.LookAndFeelManager;
import dev.guedes.gameoflife.utils.swing.LookAndFeelType;
import dev.guedes.gameoflife.views.ViewManager;
import javax.swing.SwingUtilities;

/**
 * Manages the flow and navigation of the Graphical User Interface (GUI) based on Swing.
 * <p>
 * This class serves as the orchestrator for the Swing-based windows and components,
 * ensuring that UI updates and window transitions are handled on the appropriate
 * Event Dispatch Thread.
 * </p>
 *
 * It acts as the central coordinator of the GUI layer.
 *
 * @author João Guedes
 */
public class SwingViewManager implements ViewManager {
    private final GUIMainView guiMainView;

    @Inject
    public SwingViewManager(GUIMainView guiMainView) { this.guiMainView = guiMainView; }

    public void start() {
        LookAndFeelManager.setLookAndFeel(LookAndFeelType.WINDOWS);
        SwingUtilities.invokeLater(guiMainView::display);
    }
}
