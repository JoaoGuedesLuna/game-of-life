package dev.guedes.gameoflife;

import com.google.inject.Key;
import com.google.inject.name.Names;

import dev.guedes.gameoflife.views.ViewManager;

/*
 * Copyright (c) 2026 João Guedes
 * Project : Game of Life
 * File : Application.java
 * Created on : 2026-11-09 17:00
 * Last modified: 2026-11-09 17:00
 *
 * Author : João Guedes
 * GitHub : https://github.com/JoaoGuedesLuna
 *
 * Copyright (c) 2026 João Guedes. All Rights Reserved.
 * Licensed under the MIT License.
 *
 * https://opensource.org/licenses/MIT
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
