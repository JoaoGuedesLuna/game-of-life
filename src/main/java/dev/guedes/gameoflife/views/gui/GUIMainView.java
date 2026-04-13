package dev.guedes.gameoflife.views.gui;

import com.google.inject.Inject;
import dev.guedes.gameoflife.enums.ViewAction;
import dev.guedes.gameoflife.views.View;
import dev.guedes.gameoflife.views.ViewResult;
import dev.guedes.gameoflife.views.gui.components.footer.Footer;
import dev.guedes.gameoflife.views.gui.components.frame.Frame;
import dev.guedes.gameoflife.views.gui.components.grid.GridPanel;
import dev.guedes.gameoflife.views.gui.components.header.Header;
import java.awt.BorderLayout;

import static dev.guedes.gameoflife.views.gui.styles.GUIMetadata.APP_DEFAULT_HEIGHT;
import static dev.guedes.gameoflife.views.gui.styles.GUIMetadata.APP_DEFAULT_WIDTH;
import static dev.guedes.gameoflife.views.gui.styles.GUIMetadata.APP_TITLE;

/**
 * Main application window for the program.
 *
 * @author João Guedes
 */
public class GUIMainView extends Frame implements View {
    @Inject
    public GUIMainView(Header header, GridPanel gridPanel, Footer footer) {
        super(APP_TITLE, APP_DEFAULT_WIDTH, APP_DEFAULT_HEIGHT, true);

        this.setLayout(new BorderLayout());

        this.add(header, BorderLayout.NORTH);
        this.add(gridPanel, BorderLayout.CENTER);
        this.add(footer, BorderLayout.SOUTH);
    }

    @Override
    public ViewResult<Void> display() { setVisible(true); return null; }

    @Override
    public ViewAction getAction() { return null; }
}
