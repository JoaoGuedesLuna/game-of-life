package dev.guedes.gameoflife;

import dev.guedes.gameoflife.views.cli.CLIViewManager;

/**
 * Core application runner class that handles the main execution logic.
 * This class is designed to be statically accessed and cannot be instantiated.
 *
 * @author João Guedes
 */
public class Application {
    private Application() {}

    public static void run(String[] args) { runCLIGame(); }

    public static void runCLIGame() {
        CLIViewManager cliViewManager = ApplicationInjector.getInstance(CLIViewManager.class);
        cliViewManager.start();
    }
}
