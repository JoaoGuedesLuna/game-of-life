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
public class CLIExplanationView implements View {
    private final Scanner scanner;

    @Inject
    public CLIExplanationView(Scanner scanner) { this.scanner = scanner; }

    @Override
    public ViewResult<Void> display() {
        Terminal.clear();

        System.out.println("""
                === EXPLANATION ===
                The Game of Life is not your typical computer game. It is a cellular automaton, and was invented by Cambridge mathematician John Conway.
                
                This game became widely known when it was mentioned in an article published by Scientific American in 1970. It consists of a grid of cells which, based on a few mathematical rules, can live, die or multiply. Depending on the initial conditions, the cells form various patterns throughout the course of the game.
                
                Game of Life Rules
                
                For a space that is populated:
                Each cell with one or no neighbors dies, as if by solitude.
                Each cell with four or more neighbors dies, as if by overpopulation.
                Each cell with two or three neighbors survives.
                
                For a space that is empty or unpopulated:
                Each cell with three neighbors becomes populated.
                
                More information
                
                Videos about the Game of Life
                The rules are explained in Stephen Hawking’s documentary The Meaning of Life [https://www.youtube.com/watch?v=CgOcEZinQ2I]
                John Conway himself talks about the Game of Life [https://www.youtube.com/watch?v=R9Plq-D1gEk]
                
                Interesting articles about John Conway
                John Horton Conway: the world’s most charismatic mathematician (The Guardian) [https://www.theguardian.com/science/2015/jul/23/john-horton-conway-the-most-charismatic-mathematician-in-the-world]
                John Conway Solved Mathematical Problems With His Bare Hands (Quanta Magazine) [https://www.quantamagazine.org/john-conway-solved-mathematical-problems-with-his-bare-hands-20200420/]
                
                This game is made by João Guedes <joaoguedesluna@gmail.com>
                """);

        Terminal.pause(scanner, "Press [ENTER] to return to the menu . . .");

        return ViewResult.of(ViewAction.DISPLAY_MAIN_MENU);
    }

    @Override
    public ViewAction getAction() { return ViewAction.DISPLAY_EXPLANATION; }
}
