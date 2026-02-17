package dev.guedes.gameoflife.views.gui;

import com.google.inject.Inject;
import dev.guedes.gameoflife.enums.ViewAction;
import dev.guedes.gameoflife.views.View;
import dev.guedes.gameoflife.views.ViewResult;
import dev.guedes.gameoflife.views.gui.components.frame.Frame;
import dev.guedes.gameoflife.views.gui.components.header.Header;
import java.awt.BorderLayout;

import static dev.guedes.gameoflife.views.gui.constants.GUIStyle.APP_TITLE;
import static dev.guedes.gameoflife.views.gui.constants.GUIStyle.APP_DEFAULT_HEIGHT;
import static dev.guedes.gameoflife.views.gui.constants.GUIStyle.APP_DEFAULT_WIDTH;

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
    private final Header header;

    @Inject
    public GUIMainView(Header header) {
        super(APP_TITLE, APP_DEFAULT_WIDTH, APP_DEFAULT_HEIGHT);

        this.header = header;

        initializeComponents();
    }

    @Override
    public ViewResult<Void> display() { setVisible(true); return null; }

    @Override
    public ViewAction getAction() { return null; }

    private void initializeComponents() {
        this.setLayout(new BorderLayout());
        this.add(header, BorderLayout.NORTH);
    }
}
