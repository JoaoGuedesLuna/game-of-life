package dev.guedes.gameoflife.views.cli.component;

import java.util.function.Supplier;

/**
 * Represents a selectable option in a view, associating a descriptive label
 * with a specific action to be executed.
 *
 * @param <T> The type of the result returned by the action.
 * @author João Guedes
 */
public record CLIOption<T>(String label, Supplier<T> action) {}