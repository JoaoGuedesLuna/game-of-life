package dev.guedes.gameoflife.views.cli;

import com.google.inject.Inject;
import dev.guedes.gameoflife.enums.ViewAction;
import dev.guedes.gameoflife.models.ViewOption;
import dev.guedes.gameoflife.utils.cli.Terminal;
import dev.guedes.gameoflife.utils.cli.OptionReader;
import dev.guedes.gameoflife.validators.BoundedNumberValidator;
import dev.guedes.gameoflife.validators.BoundedNumberValidatorFactory;
import dev.guedes.gameoflife.views.View;
import dev.guedes.gameoflife.views.ViewResult;
import java.util.List;

/**
 * Command-line interface main menu view for Conway's Game of Life.
 * Displays the main menu with options to start a game, view rules, or exit.
 * Handles user input and navigation to other views based on user selection.
 *
 * @author João Guedes
 */
public class CLIMainMenuView implements View {
    private final OptionReader optionReader;
    private final BoundedNumberValidator userOptionValidator;
    private final List<ViewOption<ViewResult<Void>>> menuOptions;

    @Inject
    public CLIMainMenuView(
            OptionReader optionReader,
            BoundedNumberValidatorFactory boundedNumberValidatorFactory
    ) {
        this.optionReader = optionReader;

        this.menuOptions = List.of(
                new ViewOption<>("Start game", () -> ViewResult.of(ViewAction.DISPLAY_GAME_CONFIG)),
                new ViewOption<>("Explanation", () -> ViewResult.of(ViewAction.DISPLAY_EXPLANATION)),
                new ViewOption<>("Exit",       () -> ViewResult.of(ViewAction.EXIT_APP))
        );

        this.userOptionValidator = boundedNumberValidatorFactory.create(1, menuOptions.size());
    }

    @Override
    public ViewResult<Void> display() {
        Terminal.clear();
        return readUserOption();
    }

    @Override
    public ViewAction getAction() { return ViewAction.DISPLAY_MAIN_MENU; }

    private ViewResult<Void> readUserOption() {
        return optionReader.read(
                "=== CONWAY'S GAME OF LIFE ===",
                "Choose an option: ",
                menuOptions,
                userOptionValidator
        );
    }
}
