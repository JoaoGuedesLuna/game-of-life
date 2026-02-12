package dev.guedes.gameoflife.views.cli;

import com.google.inject.Inject;
import dev.guedes.gameoflife.enums.ViewAction;
import dev.guedes.gameoflife.views.View;
import dev.guedes.gameoflife.views.ViewResult;
import java.util.Map;
import java.util.Set;
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
public class CLIViewManager {
    private final Map<ViewAction, View> staticViews;

    private ViewAction current = ViewAction.DISPLAY_MAIN_MENU;

    @Inject
    public CLIViewManager(
            Set<View> registeredViews
    ) {
        this.staticViews = registeredViews.stream()
                .collect(Collectors.toMap(View::getAction, view -> view));
    }

    public void start() {
        while (current != ViewAction.EXIT_APP) {
            ViewResult<?> result = resolveAndDisplay();
            current = result.next();
        }
    }

    private ViewResult<?> resolveAndDisplay() {
        View view = staticViews.get(current);
        if (view != null) return view.display();

        return staticViews.get(ViewAction.DISPLAY_MAIN_MENU).display();
    }
}
