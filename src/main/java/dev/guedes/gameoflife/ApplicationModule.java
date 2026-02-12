package dev.guedes.gameoflife;

import com.google.inject.AbstractModule;
import com.google.inject.Singleton;
import com.google.inject.assistedinject.FactoryModuleBuilder;
import dev.guedes.gameoflife.mappers.GridMapper;
import dev.guedes.gameoflife.mappers.impl.GridMapperImpl;
import dev.guedes.gameoflife.validators.BoundedNumberValidator;
import dev.guedes.gameoflife.validators.BoundedNumberValidatorFactory;
import dev.guedes.gameoflife.validators.PopulationValidator;
import dev.guedes.gameoflife.validators.PopulationValidatorFactory;

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

        install(new FactoryModuleBuilder()
                .implement(PopulationValidator.class, PopulationValidator.class)
                .build(PopulationValidatorFactory.class));

        bind(GridMapper.class).to(GridMapperImpl.class).in(Singleton.class);
    }
}
