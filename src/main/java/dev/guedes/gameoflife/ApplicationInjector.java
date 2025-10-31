package dev.guedes.gameoflife;

import com.google.inject.Guice;
import com.google.inject.Injector;
import lombok.Getter;

/**
 * Centralized injector for managing application dependencies using Google Guice.
 * <p>
 * This class provides a globally accessible {@link Injector} instance,
 * configured through {@link ApplicationModule}. It ensures that dependency
 * injection is consistent across the entire application by exposing helper
 * methods to retrieve the injector itself or specific instances.
 * </p>
 *
 * @author João Guedes
 */
public class ApplicationInjector {
    @Getter
    private static final Injector injector = Guice.createInjector(new ApplicationModule());

    private ApplicationInjector() {}

    public static <T> T getInstance(Class<T> clazz) { return injector.getInstance(clazz); }
}
