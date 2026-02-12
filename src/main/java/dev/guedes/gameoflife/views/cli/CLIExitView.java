package dev.guedes.gameoflife.views.cli;

import dev.guedes.gameoflife.enums.ViewAction;
import dev.guedes.gameoflife.utils.cli.Terminal;
import dev.guedes.gameoflife.views.View;
import dev.guedes.gameoflife.views.ViewResult;

/**
* Command-line interface view responsible for gracefully terminating the application.
 *
* @author João Guedes
 */
public class CLIExitView implements View {
    @Override
    public ViewResult<Void> display() {
        Terminal.clear();

        System.out.println("Thanks for playing Conway's Game of Life!");
        System.out.println("Goodbye!");

        return ViewResult.of(ViewAction.EXIT_APP);
    }

    @Override
    public ViewAction getAction() { return ViewAction.EXIT_APP; }
}
