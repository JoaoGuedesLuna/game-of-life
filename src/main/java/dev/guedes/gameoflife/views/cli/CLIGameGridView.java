package dev.guedes.gameoflife.views.cli;

import com.google.inject.Inject;
import com.google.inject.assistedinject.Assisted;
import dev.guedes.gameoflife.enums.ViewAction;
import dev.guedes.gameoflife.mappers.GridMapper;
import dev.guedes.gameoflife.models.GameConfig;
import dev.guedes.gameoflife.models.Grid;
import dev.guedes.gameoflife.utils.cli.Terminal;
import dev.guedes.gameoflife.utils.cli.OptionReader;
import dev.guedes.gameoflife.validators.BoundedNumberValidator;
import dev.guedes.gameoflife.validators.BoundedNumberValidatorFactory;
import dev.guedes.gameoflife.views.View;
import dev.guedes.gameoflife.views.ViewResult;
import dev.guedes.gameoflife.views.cli.component.CLIOption;

import java.util.List;

/**
 * Command-line interface view responsible for rendering the game grid
 * and controlling the progression of generations in Conway's Game of Life.
 *
 * @author João Guedes
 */
public class CLIGameGridView implements View {
    private static final String ALIVE_CELL = "\u001B[1m\u001B[30m\u001B[43m[x]\u001B[0m";
    private static final String DEAD_CELL = "\u001B[1m\u001B[37m[ ]\u001B[0m";

    private final OptionReader optionReader;
    private final GameConfig gameConfig;
    private final List<CLIOption<Boolean>> startOptions;
    private final List<CLIOption<ViewAction>> endOptions;
    private final BoundedNumberValidator startOptionsValidator;
    private final BoundedNumberValidator endOptionsValidator;

    private Grid gameGrid;
    private int currentGeneration = 1;

    @Inject
    public CLIGameGridView(
            @Assisted GameConfig gameConfig,
            OptionReader optionReader,
            GridMapper gridMapper,
            BoundedNumberValidatorFactory boundedNumberValidatorFactory
    ) {
        this.gameConfig = gameConfig;
        this.optionReader = optionReader;

        this.gameGrid = gridMapper.toGrid(
                gameConfig.width(),
                gameConfig.height(),
                gameConfig.population()
        );

        this.startOptions = List.of(
                new CLIOption<>("Start",                 () -> true),
                new CLIOption<>("Back to configuration", () -> false)
        );

        this.endOptions = List.of(
                new CLIOption<>("Restart with same configuration", () -> ViewAction.DISPLAY_GAME_GRID),
                new CLIOption<>("Back to configuration",           () -> ViewAction.DISPLAY_GAME_CONFIG),
                new CLIOption<>("Back to main menu",               () -> ViewAction.DISPLAY_MAIN_MENU),
                new CLIOption<>("Exit",                            () -> ViewAction.EXIT_APP)
        );

        this.startOptionsValidator = boundedNumberValidatorFactory.create(1, startOptions.size());
        this.endOptionsValidator   = boundedNumberValidatorFactory.create(1, endOptions.size());
    }

    @Override
    public ViewResult<Void> display() {
        renderFrame();

        if (!confirmStart()) return ViewResult.of(ViewAction.DISPLAY_GAME_CONFIG);

        do {
            runSimulation();

             ViewAction endAction = askEndAction();
             if (endAction != ViewAction.DISPLAY_GAME_GRID) return ViewResult.of(endAction);

            reset();
        } while (true);
    }

    @Override
    public ViewAction getAction() { return ViewAction.DISPLAY_GAME_GRID; }

    private void renderFrame() {
        Terminal.clear();
        renderStatus();
        renderGrid();
    }

    private void renderStatus() {
        System.out.printf(
                "[Width=%d, Height=%d, Generation=%d/%d, Speed=%d]%n%n",
                gameConfig.width(),
                gameConfig.height(),
                currentGeneration,
                gameConfig.generations(),
                gameConfig.speed()
        );
    }

    private void renderGrid() {
        StringBuilder sb = new StringBuilder();

        for (int row = 0; row < gameGrid.getHeight(); row++) {
            for (int col = 0; col < gameGrid.getWidth(); col++) {
                sb.append(gameGrid.isCellAlive(row, col) ? ALIVE_CELL : DEAD_CELL);
            }
            sb.append(System.lineSeparator());
        }

        System.out.println(sb);
    }

    private boolean confirmStart() {
        return optionReader.read(
                "Start game?",
                "Choose an option: ",
                startOptions,
                startOptionsValidator
        );
    }

    private void runSimulation() {
        for (int i = 0; i < gameConfig.generations() - 1; i++) {
            renderFrame();
            delayBetweenFrames();
            advanceGeneration();
        }

        renderFrame();
    }

    private void delayBetweenFrames() {
        try {
            Thread.sleep(gameConfig.speed());
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    private void advanceGeneration() {
        gameGrid.update();
        currentGeneration++;
    }

    private ViewAction askEndAction() {
        return optionReader.read(
                "Game finished. What do you want to do?",
                "Choose an option: ",
                endOptions,
                endOptionsValidator
        );
    }

    private void reset() {
        currentGeneration = 1;
        gameGrid.restoreSnapshot();
    }
}
