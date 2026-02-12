package dev.guedes.gameoflife.views;

import dev.guedes.gameoflife.enums.ViewAction;

/**
 * Encapsulates the result of a view's execution.
 *
 * @param next the next action to be performed by the application controller
 * @param payload the data passed from the view to the next state
 * @param <T> the type of the payload
 *
 * @author João Guedes
 */
public record ViewResult<T>(ViewAction next, T payload) {
    public static <T> ViewResult<T> of(ViewAction nextAction, T payload) {
        return new ViewResult<>(nextAction, payload);
    }

    public static <T> ViewResult<T> of(ViewAction nextAction) {
        return new ViewResult<>(nextAction, null);
    }
}
