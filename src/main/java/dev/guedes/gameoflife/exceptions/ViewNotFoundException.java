package dev.guedes.gameoflife.exceptions;

import dev.guedes.gameoflife.enums.ViewAction;

/**
 * Thrown when no view is registered for a given action.
 * This indicates a configuration or wiring error in the view layer,
 * where a ViewAction is requested but no corresponding View is available.
 *
 * @author João Guedes
 */
public class ViewNotFoundException extends RuntimeException {
    public ViewNotFoundException(ViewAction action) {
        super(String.format("No view registered for action [%s].", action));
    }
}
