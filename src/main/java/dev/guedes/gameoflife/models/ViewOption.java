package dev.guedes.gameoflife.models;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import java.util.function.Supplier;

/**
 * Represents a selectable option in a view, associating a descriptive label
 * with a specific action to be executed.
 *
 * @param <T> The type of the result returned by the action.
 *
 * @author João Guedes
 */
@RequiredArgsConstructor
@Getter
public class ViewOption<T> {
    private final String label;
    private final Supplier<T> action;
}