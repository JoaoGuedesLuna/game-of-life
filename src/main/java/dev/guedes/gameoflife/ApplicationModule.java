package dev.guedes.gameoflife;

import com.google.inject.AbstractModule;
import com.google.inject.assistedinject.FactoryModuleBuilder;
import dev.guedes.gameoflife.validators.BoundedNumberValidator;
import dev.guedes.gameoflife.validators.BoundedNumberValidatorFactory;


/**
 * Guice configuration module for the application.
 * This module defines the bindings between interfaces and their concrete
 * implementations, enabling dependency injection across the application.
 *
 * @author João Guedes
 */
public class ApplicationModule extends AbstractModule {
    @Override
    protected void configure() {
        install(new FactoryModuleBuilder()
                .implement(BoundedNumberValidator.class, BoundedNumberValidator.class)
                .build(BoundedNumberValidatorFactory.class));
    }
}
