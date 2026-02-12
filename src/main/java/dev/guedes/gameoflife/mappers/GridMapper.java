package dev.guedes.gameoflife.mappers;

import dev.guedes.gameoflife.models.Grid;

/**
 * Mapper interface that converts a textual population description into a Grid object.
 *
 * @author João Guedes
 */
public interface GridMapper {
    Grid toGrid(int width, int height, String population);
}
