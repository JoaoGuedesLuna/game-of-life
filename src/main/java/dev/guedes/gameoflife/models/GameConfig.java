package dev.guedes.gameoflife.models;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * Represents the configuration settings for a Game of Life simulation.
 * This immutable class holds all the parameters needed to initialize and run a game,
 * including board dimensions, number of generations, simulation speed, and initial population.
 *
 * @author João Guedes
 */
@RequiredArgsConstructor
@Getter
public class GameConfig {
    private final int width;
    private final int height;
    private final int generations;
    private final int speed;
    private final String population;
}
