package dev.guedes.gameoflife.config;

/**
 * This class defines configuration limits for the Game of Life simulation.
 *
 * @author João Guedes
 */
public final class GameLimits {
    public static final int MIN_WIDTH = 3;
    public static final int MAX_WIDTH = 200;

    public static final int MIN_HEIGHT = 3;
    public static final int MAX_HEIGHT = 200;

    public static final int MIN_GENERATIONS = 1;
    public static final int MAX_GENERATIONS = 10_000;

    public static final int MIN_SPEED = 50;
    public static final int MAX_SPEED = 5_000;

    private GameLimits() {}
}
