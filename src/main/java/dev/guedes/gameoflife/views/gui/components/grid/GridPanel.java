package dev.guedes.gameoflife.views.gui.components.grid;

import dev.guedes.gameoflife.models.Grid;
import dev.guedes.gameoflife.enums.Cell;
import lombok.Setter;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import java.awt.BasicStroke;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Rectangle;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Arrays;

import static dev.guedes.gameoflife.views.gui.styles.GUIColors.GRID_ALIVE_COLOR;
import static dev.guedes.gameoflife.views.gui.styles.GUIColors.GRID_BG;
import static dev.guedes.gameoflife.views.gui.styles.GUIColors.GRID_LINE_COLOR;
import static dev.guedes.gameoflife.views.gui.styles.GUIDimensions.GRID_CELL_SIZE;

/**
 * Component responsible for rendering the Game of Life grid with zoom support.
 *
 * @author João Guedes
 */
public class GridPanel extends JPanel {
    private Grid grid;

    @Setter
    private Runnable onGridChanged;

    public GridPanel() {
        this.setBackground(GRID_BG);
        this.addMouseListener(createMouseListener());
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        ensureGridInitialized();
        if (grid == null) return;

        Graphics2D g2 = (Graphics2D) g;
        enableRenderingHints(g2);

        drawVisibleCells(g2);
        drawGridLines(g2);
    }

    public void clear() {
        if (grid == null) return;
        grid.clear();
        repaint();
    }

    public void reset() {
        if (grid == null) return;
        grid.restoreSnapshot();
        repaint();
    }

    public void save() {
        if (grid != null) {
            grid.saveSnapshot();
        }
    }

    public void advanceGeneration() {
        if (grid == null) return;

        grid.update();
        repaint();

        if (onGridChanged != null) onGridChanged.run();
    }

    public boolean hasLivingCells() { return grid != null && grid.hasLivingCells(); }

    private MouseAdapter createMouseListener() {
        return new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                if (SwingUtilities.isLeftMouseButton(e)) {
                    handleClick(e.getX(), e.getY());
                }
            }
        };
    }

    private void handleClick(int x, int y) {
        if (grid == null) return;

        int col = x / GRID_CELL_SIZE;
        int row = y / GRID_CELL_SIZE;

        try {
            grid.toggleCell(row, col);

            if (onGridChanged != null) onGridChanged.run();

            repaint();
        } catch (Exception ignored) {}
    }

    private void ensureGridInitialized() {
        if (grid != null) return;

        int width = getWidth();
        int height = getHeight();

        if (width <= 0 || height <= 0) return;

        int cols = width / GRID_CELL_SIZE;
        int rows = height / GRID_CELL_SIZE;

        grid = createEmptyGrid(rows, cols);

        setPreferredSize(new Dimension(
                cols * GRID_CELL_SIZE,
                rows * GRID_CELL_SIZE
        ));

        revalidate();
    }

    private Grid createEmptyGrid(int rows, int cols) {
        Cell[][] cells = new Cell[rows][cols];
        for (Cell[] row : cells) {
            Arrays.fill(row, Cell.DEAD);
        }
        return new Grid(cells);
    }

    private void enableRenderingHints(Graphics2D g) {
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
    }

    private void drawVisibleCells(Graphics2D g) {
        Rectangle clip = g.getClipBounds();

        int cellSize = GRID_CELL_SIZE;

        int firstRow = Math.max(0, clip.y / cellSize);
        int firstCol = Math.max(0, clip.x / cellSize);

        int lastRow = Math.min(grid.getHeight(), (clip.y + clip.height) / cellSize + 1);
        int lastCol = Math.min(grid.getWidth(), (clip.x + clip.width) / cellSize + 1);

        g.setColor(GRID_ALIVE_COLOR);

        for (int row = firstRow; row < lastRow; row++) {
            int y = row * cellSize + 1;

            for (int col = firstCol; col < lastCol; col++) {
                if (grid.isCellAlive(row, col)) {
                    int x = col * cellSize + 1;
                    g.fillRect(x, y, cellSize - 1, cellSize - 1);
                }
            }
        }
    }

    private void drawGridLines(Graphics2D g) {
        Rectangle clip = g.getClipBounds();

        g.setColor(GRID_LINE_COLOR);
        g.setStroke(new BasicStroke(1f));

        int cellSize = GRID_CELL_SIZE;

        int widthPx = grid.getWidth() * cellSize;
        int heightPx = grid.getHeight() * cellSize;

        drawHorizontalLines(g, clip, cellSize, widthPx);
        drawVerticalLines(g, clip, cellSize, heightPx);
    }

    private void drawHorizontalLines(Graphics2D g, Rectangle clip, int cellSize, int widthPx) {
        for (int row = 0; row <= grid.getHeight(); row++) {
            int y = row * cellSize;
            if (isInVerticalClip(y, clip)) {
                g.drawLine(0, y, widthPx, y);
            }
        }
    }

    private void drawVerticalLines(Graphics2D g, Rectangle clip, int cellSize, int heightPx) {
        for (int col = 0; col <= grid.getWidth(); col++) {
            int x = col * cellSize;
            if (isInHorizontalClip(x, clip)) {
                g.drawLine(x, 0, x, heightPx);
            }
        }
    }

    private boolean isInVerticalClip(int y, Rectangle clip) {
        return y >= clip.y && y <= clip.y + clip.height;
    }

    private boolean isInHorizontalClip(int x, Rectangle clip) {
        return x >= clip.x && x <= clip.x + clip.width;
    }
}
