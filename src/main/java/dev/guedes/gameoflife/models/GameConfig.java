package dev.guedes.gameoflife.models;

/**
 * Represents the configuration settings for a Game of Life simulation.
 * This immutable class holds all the parameters needed to initialize and run a game,
 * including board dimensions, number of generations, simulation speed, and initial population.
 *
 * @author João Guedes
 */
public record GameConfig(int width, int height, int generations, int speed, String population) {}
