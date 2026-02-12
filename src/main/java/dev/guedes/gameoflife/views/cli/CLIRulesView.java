package dev.guedes.gameoflife.views.cli;

import com.google.inject.Inject;
import dev.guedes.gameoflife.enums.ViewAction;
import dev.guedes.gameoflife.utils.cli.Terminal;
import dev.guedes.gameoflife.views.View;
import dev.guedes.gameoflife.views.ViewResult;
import java.util.Scanner;

/**
 * Command-line interface view for displaying the rules of Conway's Game of Life.
 * Shows the four fundamental rules of the game and provides navigation back to the main menu.
 *
 * @author João Guedes
 */
public class CLIRulesView implements View {
    private final Scanner scanner;

    @Inject
    public CLIRulesView(Scanner scanner) { this.scanner = scanner; }

    @Override
    public ViewResult<Void> display() {
        Terminal.clear();

        System.out.println("=== GAME RULES ===");
        System.out.println("1. Any live cell with fewer than two live neighbors dies of loneliness in the next generation.");
        System.out.println("2. Any live cell with two or three live neighbors remains alive in the next generation.");
        System.out.println("3. Any live cell with more than three live neighbors dies of overpopulation in the next generation.");
        System.out.println("4. Any dead cell with exactly three live neighbors becomes a live cell in the next generation.");

        Terminal.pause(scanner, "Press [ENTER] to return to the menu . . .");

        return ViewResult.of(ViewAction.DISPLAY_MAIN_MENU);
    }

    @Override
    public ViewAction getAction() { return ViewAction.DISPLAY_GAME_RULES; }
}
