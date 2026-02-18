package dev.guedes.gameoflife.views.gui;

import com.google.inject.Inject;
import dev.guedes.gameoflife.enums.ViewAction;
import dev.guedes.gameoflife.views.View;
import dev.guedes.gameoflife.views.ViewResult;
import dev.guedes.gameoflife.views.gui.components.frame.Frame;
import dev.guedes.gameoflife.views.gui.components.grid.GridPanel;
import dev.guedes.gameoflife.views.gui.components.header.Header;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import java.awt.BorderLayout;

import static dev.guedes.gameoflife.views.gui.constants.GUIStyle.APP_TITLE;
import static dev.guedes.gameoflife.views.gui.constants.GUIStyle.APP_DEFAULT_HEIGHT;
import static dev.guedes.gameoflife.views.gui.constants.GUIStyle.APP_DEFAULT_WIDTH;
import static dev.guedes.gameoflife.views.gui.constants.GUIStyle.GRID_BG_COLOR;

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
    private final GridPanel gridPanel;

    @Inject
    public GUIMainView(Header header, GridPanel gridPanel) {
        super(APP_TITLE, APP_DEFAULT_WIDTH, APP_DEFAULT_HEIGHT);

        this.header = header;
        this.gridPanel = gridPanel;

        initializeComponents();
    }

    @Override
    public ViewResult<Void> display() { setVisible(true); return null; }

    @Override
    public ViewAction getAction() { return null; }

    private void initializeComponents() {
        this.setLayout(new BorderLayout());

        JScrollPane scrollGridPanel = new JScrollPane(gridPanel);
        scrollGridPanel.getViewport().setBackground(GRID_BG_COLOR);
        scrollGridPanel.setBorder(null);
        scrollGridPanel.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        scrollGridPanel.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);

        this.add(header, BorderLayout.NORTH);
        this.add(scrollGridPanel, BorderLayout.CENTER);
    }
}
