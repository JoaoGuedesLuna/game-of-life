package dev.guedes.gameoflife.views.gui;

import dev.guedes.gameoflife.enums.ViewAction;
import dev.guedes.gameoflife.views.View;
import dev.guedes.gameoflife.views.ViewResult;
import dev.guedes.gameoflife.views.gui.components.frame.Frame;

/**
 * Main application window for the program.
 * <p>
 * The window is divided into two panels:
 * <ul>
 *   <li>The panel on the left – manages the list of participants</li>
 *   <li>The panel on the right – performs and displays prayer assignments</li>
 * </ul>
 * </p>
 *
 * @author João Guedes
 */
public class GUIMainView extends Frame implements View {
    public GUIMainView() { super("Conway’s Game of Life", 700, 500); }

    @Override
    public ViewResult<Void> display() { setVisible(true); return null; }

    @Override
    public ViewAction getAction() { return null; }
}
