package dev.guedes.gameoflife.views.cli;

import dev.guedes.gameoflife.models.GameConfig;

/**
 * Factory interface for creating instances of {@link CLIGameGridView}.
 *
 * @author João Guedes
 */
public interface CLIGameGridViewFactory {
    CLIGameGridView create(GameConfig gameConfig);
}
