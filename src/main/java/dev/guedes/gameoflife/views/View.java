package dev.guedes.gameoflife.views;

import dev.guedes.gameoflife.enums.ViewAction;

/**
 * Represents a contract for application views.
 * <p>
 * Every view should implement this interface to define
 * how it is displayed in the UI.
 * </p>
 *
 * @author João Guedes
 */
public interface View {
    ViewResult<?> display();
    ViewAction getAction();
}
