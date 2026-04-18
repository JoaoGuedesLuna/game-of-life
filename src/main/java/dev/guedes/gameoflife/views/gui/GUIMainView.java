package dev.guedes.gameoflife.views.gui;

import com.google.inject.Inject;
import dev.guedes.gameoflife.enums.ViewAction;
import dev.guedes.gameoflife.utils.swing.ScreenUtils;
import dev.guedes.gameoflife.views.View;
import dev.guedes.gameoflife.views.ViewResult;
import dev.guedes.gameoflife.views.gui.components.ComponentFactory;
import dev.guedes.gameoflife.views.gui.components.footer.Footer;
import dev.guedes.gameoflife.views.gui.components.frame.Frame;
import dev.guedes.gameoflife.views.gui.components.grid.GridPanel;
import dev.guedes.gameoflife.views.gui.components.header.Header;
import javax.swing.JScrollPane;
import javax.swing.JViewport;
import javax.swing.SwingUtilities;
import java.awt.BorderLayout;
import java.awt.Cursor;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import static dev.guedes.gameoflife.views.gui.styles.GUIDimensions.GRID_CELL_SIZE;
import static dev.guedes.gameoflife.views.gui.styles.GUIMetadata.APP_TITLE;
import static javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER;
import static javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER;

/**
 * Main application window for the program.
 *
 * @author João Guedes
 */
public class GUIMainView extends Frame implements View {
    @Inject
    public GUIMainView(Header header, GridPanel gridPanel, Footer footer) {
        super(
                APP_TITLE,
                ScreenUtils.getScreenWidth() + GRID_CELL_SIZE * 2,
                ScreenUtils.getScreenHeight() - GRID_CELL_SIZE,
                true,
                EXIT_ON_CLOSE
        );

        this.setLayout(new BorderLayout());

        this.add(header, BorderLayout.NORTH);
        this.add(createScrollableGrid(gridPanel), BorderLayout.CENTER);
        this.add(footer, BorderLayout.SOUTH);
    }

    @Override
    public ViewResult<Void> display() { setVisible(true); return null; }

    @Override
    public ViewAction getAction() { return null; }

    private JScrollPane createScrollableGrid(GridPanel gridPanel) {
        JScrollPane scrollPane = ComponentFactory.scroll(gridPanel, HORIZONTAL_SCROLLBAR_NEVER, VERTICAL_SCROLLBAR_NEVER);

        enableDragToScroll(gridPanel, scrollPane);

        return scrollPane;
    }

    private void enableDragToScroll(GridPanel gridPanel, JScrollPane scrollPane) {
        final Point lastPoint = new Point();

        gridPanel.setCursor(new Cursor(Cursor.HAND_CURSOR));
        scrollPane.getViewport().setCursor(new Cursor(Cursor.HAND_CURSOR));

        MouseAdapter adapter = new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                if (SwingUtilities.isRightMouseButton(e)) {
                    lastPoint.setLocation(e.getPoint());
                    gridPanel.setCursor(new Cursor(Cursor.MOVE_CURSOR));
                }
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                if (SwingUtilities.isRightMouseButton(e)) {
                    gridPanel.setCursor(new Cursor(Cursor.HAND_CURSOR));
                }
            }

            @Override
            public void mouseDragged(MouseEvent e) {
                if (!SwingUtilities.isRightMouseButton(e)) return;

                JViewport viewport = scrollPane.getViewport();
                Point viewPos = viewport.getViewPosition();

                int dx = e.getX() - lastPoint.x;
                int dy = e.getY() - lastPoint.y;

                viewPos.translate(-dx, -dy);

                gridPanel.scrollRectToVisible(new Rectangle(viewPos, viewport.getSize()));

                lastPoint.setLocation(e.getPoint());
            }
        };

        gridPanel.addMouseListener(adapter);
        gridPanel.addMouseMotionListener(adapter);
    }
}
