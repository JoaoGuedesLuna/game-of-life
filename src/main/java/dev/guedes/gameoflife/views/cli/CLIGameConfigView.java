package dev.guedes.gameoflife.views.cli;

import com.google.inject.Inject;
import dev.guedes.gameoflife.config.GameLimits;
import dev.guedes.gameoflife.enums.ViewAction;
import dev.guedes.gameoflife.models.GameConfig;
import dev.guedes.gameoflife.utils.cli.Terminal;
import dev.guedes.gameoflife.utils.cli.InputReader;
import dev.guedes.gameoflife.validators.BoundedNumberValidator;
import dev.guedes.gameoflife.validators.BoundedNumberValidatorFactory;
import dev.guedes.gameoflife.validators.PopulationValidator;
import dev.guedes.gameoflife.validators.PopulationValidatorFactory;
import dev.guedes.gameoflife.views.View;
import dev.guedes.gameoflife.views.ViewResult;
import java.util.Set;

/**
 * Command-line interface view for configuring Conway's Game of Life.
 * Allows the user to set game parameters such as board dimensions, number of generations,
 * simulation speed, and initial population pattern.
 * Provides validation for all inputs and allows returning to the main menu at any time.
 *
 * @author João Guedes
 */
public class CLIGameConfigView implements View{
    private static final String BACK_COMMAND = "back";

    private final InputReader inputReader;
    private final BoundedNumberValidator widthValidator;
    private final BoundedNumberValidator heightValidator;
    private final BoundedNumberValidator generationsValidator;
    private final BoundedNumberValidator speedValidator;
    private final PopulationValidatorFactory populationValidatorFactory;

    @Inject
    private CLIGameConfigView(
            InputReader inputReader,
            BoundedNumberValidatorFactory boundedNumberValidatorFactory,
            PopulationValidatorFactory populationValidatorFactory
    ) {
        this.inputReader = inputReader;
        this.populationValidatorFactory = populationValidatorFactory;

        this.widthValidator = boundedNumberValidatorFactory
                .create(GameLimits.MIN_WIDTH, GameLimits.MAX_WIDTH);

        this.heightValidator = boundedNumberValidatorFactory
                .create(GameLimits.MIN_HEIGHT, GameLimits.MAX_HEIGHT);

        this.generationsValidator = boundedNumberValidatorFactory
                .create(GameLimits.MIN_GENERATIONS, GameLimits.MAX_GENERATIONS);

        this.speedValidator = boundedNumberValidatorFactory
                .create(GameLimits.MIN_SPEED, GameLimits.MAX_SPEED);
    }

    @Override
    public ViewResult<GameConfig> display() {
        Terminal.clear();

        System.out.println("=== GAME CONFIGURATION ===");
        System.out.println("Type 'back' at any time to return to the main menu.");

        GameConfig config = readConfig();

        return (config == null)
                ? ViewResult.of(ViewAction.DISPLAY_MAIN_MENU)
                : ViewResult.of(ViewAction.DISPLAY_GAME_GRID, config);
    }

    @Override
    public ViewAction getAction() { return ViewAction.DISPLAY_GAME_CONFIG; }

    private GameConfig readConfig() {
        Integer width = readWidth();
        if (width == null) return null;

        Integer height = readHeight();
        if (height == null) return null;

        Integer generations = readGenerations();
        if (generations == null) return null;

        Integer speed = readSpeed();
        if (speed == null) return null;

        String population = readPopulation(width, height);
        if (population == null) return null;

        return new GameConfig(width, height, generations, speed, population);
    }

    private Integer readWidth() {
        System.out.println();

        return this.inputReader.readInt(
                """
                Board width (columns)
                Defines how many cells each row will have.
                >\s""",
                widthValidator,
                Set.of(BACK_COMMAND)
        ).orElse(null);
    }

    private Integer readHeight() {
        System.out.println();

        return this.inputReader.readInt(
                """
                Board height (rows)
                Defines how many rows the board will have.
                >\s""",
                heightValidator,
                Set.of(BACK_COMMAND)
        ).orElse(null);
    }

    private Integer readGenerations() {
        System.out.println();

        return this.inputReader.readInt(
                """
                Number of generations
                How many iterations the simulation will run.
                >\s""",
                generationsValidator,
                Set.of(BACK_COMMAND)
        ).orElse(null);
    }

    private Integer readSpeed() {
        System.out.println();

        return this.inputReader.readInt(
                """
                Simulation speed (ms)
                Delay in milliseconds between generations.
                >\s""",
                speedValidator,
                Set.of(BACK_COMMAND)
        ).orElse(null);
    }

    private String readPopulation(int width, int height) {
        PopulationValidator populationValidator = populationValidatorFactory.create(width, height);

        System.out.println();

        return this.inputReader.readString(
                """
                Initial population pattern
                Use '1' for alive cells, '0' for dead cells and '#' to separate rows.
                Example: 001#101#011
                >\s""",
                populationValidator,
                Set.of(BACK_COMMAND)
        ).orElse(null);
    }
}
