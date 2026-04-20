package dev.guedes.gameoflife.views;

/**
 * Unified interface for managing the application's user interface.
 * <p>
 * This interface defines the contract for any UI implementation (CLI, GUI, etc.),
 * allowing the application to bootstrap different presentation layers
 * interchangeably through dependency injection.
 * </p>
 *
 * @author João Guedes
 */
public interface ViewManager {
    void start();
}
