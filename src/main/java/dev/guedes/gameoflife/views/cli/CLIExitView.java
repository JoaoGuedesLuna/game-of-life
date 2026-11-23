package dev.guedes.gameoflife.views.cli;

import dev.guedes.gameoflife.enums.ViewAction;
import dev.guedes.gameoflife.utils.cli.Terminal;
import dev.guedes.gameoflife.views.View;
import dev.guedes.gameoflife.views.ViewResult;

/*
 * Copyright (c) 2026 João Guedes
 * Project : Game of Life
 * File : CLIExitView.java
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
