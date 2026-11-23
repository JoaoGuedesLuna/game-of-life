package dev.guedes.gameoflife.views.cli;

import com.google.inject.Inject;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.function.Supplier;
import java.util.stream.Collectors;

import dev.guedes.gameoflife.enums.ViewAction;
import dev.guedes.gameoflife.exceptions.InvalidPayloadException;
import dev.guedes.gameoflife.exceptions.ViewNotFoundException;
import dev.guedes.gameoflife.models.GameConfig;
import dev.guedes.gameoflife.views.View;
import dev.guedes.gameoflife.views.ViewManager;
import dev.guedes.gameoflife.views.ViewResult;

/*
 * Copyright (c) 2026 João Guedes
 * Project : Game of Life
 * File : CLIViewManager.java
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
public class CLIViewManager implements ViewManager {
    private final Map<ViewAction, Supplier<View>> viewsMap;

    private ViewAction currentAction;
    private Object currentPayload;

    @Inject
    public CLIViewManager(
            Set<View> registeredViews,
            CLIGameGridViewFactory gameGridViewFactory
    ) {
        this.viewsMap = new HashMap<>(
                registeredViews.stream()
                        .collect(Collectors.toMap(View::getAction, view -> () -> view))
        );

        this.viewsMap.put(
                ViewAction.DISPLAY_GAME_GRID,
                () -> gameGridViewFactory.create(getPayloadAs(GameConfig.class))
        );
    }

    public void start() {
        currentAction = ViewAction.DISPLAY_MAIN_MENU;

        ViewResult<?> result = resolveView().display();

        do {
            currentAction = result.nextAction();
            currentPayload = result.payload();
            result = resolveView().display();
        } while (!isExitAction(currentAction));
    }

    @SuppressWarnings("unchecked")
    private <T> T getPayloadAs(Class<T> type) {
        if (type.isInstance(currentPayload)) {
            return (T) currentPayload;
        }
        throw new InvalidPayloadException(currentAction, type, currentPayload);
    }

    private View resolveView() {
        Supplier<View> supplierView = viewsMap.get(currentAction);

        if (supplierView == null) throw new ViewNotFoundException(currentAction);

        return supplierView.get();
    }

    private boolean isExitAction(ViewAction action) { return action == ViewAction.EXIT_APP; }
}
