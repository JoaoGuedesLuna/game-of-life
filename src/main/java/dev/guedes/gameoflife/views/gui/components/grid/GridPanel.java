package dev.guedes.gameoflife.views.gui.components.grid;

import dev.guedes.gameoflife.models.Grid;
import dev.guedes.gameoflife.enums.Cell;
import javax.swing.JPanel;
import javax.swing.JViewport;
import javax.swing.SwingUtilities;
import java.awt.BasicStroke;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Rectangle;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Arrays;

import static dev.guedes.gameoflife.config.GameLimits.MAX_HEIGHT;
import static dev.guedes.gameoflife.config.GameLimits.MAX_WIDTH;
import static dev.guedes.gameoflife.views.gui.constants.GUIStyle.GRID_BG_COLOR;
import static dev.guedes.gameoflife.views.gui.constants.GUIStyle.GRID_CELL_ALIVE_COLOR;
import static dev.guedes.gameoflife.views.gui.constants.GUIStyle.GRID_LINE_COLOR;
import static dev.guedes.gameoflife.views.gui.constants.GUIStyle.GRID_MAX_ZOOM;
import static dev.guedes.gameoflife.views.gui.constants.GUIStyle.GRID_MIN_ZOOM;

/**
 * Component responsible for rendering the Game of Life grid with zoom support.
 *
 * @author João Guedes
 */
public class GridPanel extends JPanel {
    private Grid grid;
    private int cellSize = 20;

    public GridPanel() {
        this.setBackground(GRID_BG_COLOR);
        this.setupInteractions();
        this.initializeEmptyGrid();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (grid == null) return;

        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        drawCells(g2);
        drawGridLines(g2);
    }

    private void drawCells(Graphics2D g) {
        g.setColor(GRID_CELL_ALIVE_COLOR);
        Rectangle clip = g.getClipBounds();

        int firstRow = Math.max(0, clip.y / cellSize);
        int lastRow = Math.min(grid.getHeight(), (clip.y + clip.height) / cellSize + 1);
        int firstCol = Math.max(0, clip.x / cellSize);
        int lastCol = Math.min(grid.getWidth(), (clip.x + clip.width) / cellSize + 1);

        for (int row = firstRow; row < lastRow; row++) {
            for (int col = firstCol; col < lastCol; col++) {
                if (grid.isCellAlive(row, col)) {
                    g.fillRect(col * cellSize + 1, row * cellSize + 1, cellSize - 1, cellSize - 1);
                }
            }
        }
    }

    private void drawGridLines(Graphics2D g) {
        if (cellSize < GRID_MIN_ZOOM) return;

        g.setColor(GRID_LINE_COLOR);

        g.setStroke(new BasicStroke(1.0f));

        int fullWidth = grid.getWidth() * cellSize;
        int fullHeight = grid.getHeight() * cellSize;

        Rectangle clip = g.getClipBounds();

        for (int i = 0; i <= grid.getWidth(); i++) {
            int x = i * cellSize;
            if (x >= clip.x && x <= (clip.x + clip.width)) {
                g.drawLine(x, 0, x, fullHeight);
            }
        }

        for (int i = 0; i <= grid.getHeight(); i++) {
            int y = i * cellSize;
            if (y >= clip.y && y <= (clip.y + clip.height)) {
                g.drawLine(0, y, fullWidth, y);
            }
        }
    }

    private void setupInteractions() {
        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                handleMouseClick(e.getX(), e.getY());
            }
        });

        this.addMouseWheelListener(e -> {
            Point mousePoint = e.getPoint();

            if (e.getWheelRotation() < 0) {
                zoomIn(mousePoint);
            } else {
                zoomOut(mousePoint);
            }
        });
    }

    private void initializeEmptyGrid() {
        Cell[][] initialCells = new Cell[MAX_HEIGHT][MAX_WIDTH];

        for (Cell[] initialCell : initialCells) {
            Arrays.fill(initialCell, Cell.DEAD);
        }

        updateGrid(new Grid(initialCells));
    }

    private void updateGrid(Grid grid) {
        this.grid = grid;
        updatePreferredSize();
    }

    private void handleMouseClick(int x, int y) {
        if (grid == null) return;

        int col = x / cellSize;
        int row = y / cellSize;

        try {
            grid.toggleCell(row, col);
            repaint();
        } catch (Exception ignored) {}
    }

    private void zoomIn(Point pivot) {
        if (cellSize < GRID_MAX_ZOOM) applyZoom(2, pivot);
    }

    private void zoomOut(Point pivot) {
        if (cellSize > GRID_MIN_ZOOM) applyZoom(-2, pivot);
    }

    private void applyZoom(int delta, Point pivot) {
        if (grid == null) return;

        double oldX = pivot.getX() / cellSize;
        double oldY = pivot.getY() / cellSize;

        cellSize += delta;
        updatePreferredSize();

        SwingUtilities.invokeLater(() -> {
            Container parent = getParent();
            if (parent instanceof JViewport viewport) {
                int newMouseX = (int) (oldX * cellSize);
                int newMouseY = (int) (oldY * cellSize);

                Point viewPosition = new Point(
                        newMouseX - pivot.x + viewport.getViewPosition().x,
                        newMouseY - pivot.y + viewport.getViewPosition().y
                );

                viewPosition.x = Math.max(0, viewPosition.x);
                viewPosition.y = Math.max(0, viewPosition.y);

                viewport.setViewPosition(viewPosition);
            }
        });
    }

    private void updatePreferredSize() {
        if (grid != null) {
            int width = grid.getWidth() * cellSize;
            int height = grid.getHeight() * cellSize;
            this.setPreferredSize(new Dimension(width, height));
            this.revalidate();
            this.repaint();
        }
    }
}
