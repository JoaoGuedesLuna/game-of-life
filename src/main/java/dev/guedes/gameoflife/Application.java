package dev.guedes.gameoflife;

import com.google.inject.Key;
import com.google.inject.name.Names;
import dev.guedes.gameoflife.views.ViewManager;

/**
 * Core application runner class that handles the main execution logic.
 * This class is designed to be statically accessed and cannot be instantiated.
 *
 * @author João Guedes
 */
public class Application {
    private Application() {}

    public static void run(String[] args) {
        String mode = (args != null && args.length > 0 && args[0].equalsIgnoreCase("--cli"))
                ? "CLI"
                : "Swing";

        ViewManager viewManager = ApplicationInjector.getInstance(Key.get(ViewManager.class, Names.named(mode)));
        viewManager.start();
    }
}
