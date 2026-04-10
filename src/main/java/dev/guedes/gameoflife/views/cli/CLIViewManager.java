package dev.guedes.gameoflife.views.cli;

import com.google.inject.Inject;
import dev.guedes.gameoflife.enums.ViewAction;
import dev.guedes.gameoflife.exceptions.InvalidPayloadException;
import dev.guedes.gameoflife.exceptions.ViewNotFoundException;
import dev.guedes.gameoflife.models.GameConfig;
import dev.guedes.gameoflife.views.View;
import dev.guedes.gameoflife.views.ViewManager;
import dev.guedes.gameoflife.views.ViewResult;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.function.Supplier;
import java.util.stream.Collectors;

/**
 * Manages the flow and navigation of the CLI-based user interface.
 * <p>
 * This class is responsible for controlling which view is displayed,
 * handling transitions between views, and maintaining the current
 * application state during execution.
 * </p>
 *
 * It acts as the central coordinator of the CLI UI layer.
 *
 * @author João Guedes
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
                        .collect(Collectors.toMap(
                                View::getAction,
                                view -> () -> view
                        ))
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
            currentAction = result.next();
            currentPayload = result.payload();
            result = resolveView().display();
        } while (!isExitAction(currentAction));
    }

    private boolean isExitAction(ViewAction action) { return action == ViewAction.EXIT_APP; }


    private View resolveView() {
        Supplier<View> supplierViews = viewsMap.get(currentAction);

        if (supplierViews == null) throw new ViewNotFoundException(currentAction);

        return supplierViews.get();
    }

    @SuppressWarnings("unchecked")
    private <T> T getPayloadAs(Class<T> type) {
        if (type.isInstance(currentPayload)) {
            return (T) currentPayload;
        }
        throw new InvalidPayloadException(currentAction, type, currentPayload);
    }
}
