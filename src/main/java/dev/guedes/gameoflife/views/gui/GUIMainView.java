package dev.guedes.gameoflife.views.gui;

import com.google.inject.Inject;
import dev.guedes.gameoflife.enums.ViewAction;
import dev.guedes.gameoflife.utils.swing.ScreenUtils;
import dev.guedes.gameoflife.views.View;
import dev.guedes.gameoflife.views.ViewResult;
import dev.guedes.gameoflife.views.gui.components.footer.Footer;
import dev.guedes.gameoflife.views.gui.components.frame.Frame;
import dev.guedes.gameoflife.views.gui.components.grid.GridPanel;
import dev.guedes.gameoflife.views.gui.components.header.Header;
import javax.swing.JScrollPane;
import java.awt.BorderLayout;

import static dev.guedes.gameoflife.views.gui.styles.GUIDimensions.GRID_CELL_SIZE;
import static dev.guedes.gameoflife.views.gui.styles.GUIMetadata.APP_TITLE;

/**
 * Main application window for the program.
 *
 * @author João Guedes
 */
public class GUIMainView extends Frame implements View {
    @Inject
    public GUIMainView(Header header, GridPanel gridPanel, Footer footer) {
        super(APP_TITLE, ScreenUtils.getScreenWidth() + GRID_CELL_SIZE * 2, ScreenUtils.getScreenHeight() - GRID_CELL_SIZE, true);

        this.setLayout(new BorderLayout());

        this.add(header, BorderLayout.NORTH);

        JScrollPane scrollPane = new JScrollPane(gridPanel);
        this.add(scrollPane, BorderLayout.CENTER);

        this.add(footer, BorderLayout.SOUTH);
    }

    @Override
    public ViewResult<Void> display() { setVisible(true); return null; }

    @Override
    public ViewAction getAction() { return null; }
}
