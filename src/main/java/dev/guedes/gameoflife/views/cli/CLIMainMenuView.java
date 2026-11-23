package dev.guedes.gameoflife.views.cli;

import com.google.inject.Inject;
import java.util.List;

import dev.guedes.gameoflife.enums.ViewAction;
import dev.guedes.gameoflife.utils.cli.Terminal;
import dev.guedes.gameoflife.utils.cli.OptionReader;
import dev.guedes.gameoflife.views.View;
import dev.guedes.gameoflife.views.ViewResult;
import dev.guedes.gameoflife.views.cli.component.CLIOption;

/*
 * Copyright (c) 2026 João Guedes
 * Project : Game of Life
 * File : CLIMainMenuView.java
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
public class CLIMainMenuView implements View {
    private final OptionReader optionReader;
    private final List<CLIOption<ViewResult<Void>>> menuOptions;

    @Inject
    public CLIMainMenuView(OptionReader optionReader) {
        this.optionReader = optionReader;

        this.menuOptions = List.of(
                new CLIOption<>("Start game", () -> ViewResult.of(ViewAction.DISPLAY_GAME_CONFIG)),
                new CLIOption<>("Explanation", () -> ViewResult.of(ViewAction.DISPLAY_EXPLANATION)),
                new CLIOption<>("Exit",       () -> ViewResult.of(ViewAction.EXIT_APP))
        );
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
                menuOptions
        );
    }
}
