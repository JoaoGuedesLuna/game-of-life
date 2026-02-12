package dev.guedes.gameoflife;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.inject.Singleton;
import com.google.inject.assistedinject.FactoryModuleBuilder;
import com.google.inject.multibindings.Multibinder;
import dev.guedes.gameoflife.mappers.GridMapper;
import dev.guedes.gameoflife.mappers.impl.GridMapperImpl;
import dev.guedes.gameoflife.utils.cli.InputReader;
import dev.guedes.gameoflife.utils.cli.OptionReader;
import dev.guedes.gameoflife.validators.BoundedNumberValidator;
import dev.guedes.gameoflife.validators.BoundedNumberValidatorFactory;
import dev.guedes.gameoflife.validators.PopulationValidator;
import dev.guedes.gameoflife.validators.PopulationValidatorFactory;
import dev.guedes.gameoflife.views.View;
import dev.guedes.gameoflife.views.cli.CLIExitView;
import dev.guedes.gameoflife.views.cli.CLIGameConfigView;
import dev.guedes.gameoflife.views.cli.CLIMainMenuView;
import dev.guedes.gameoflife.views.cli.CLIRulesView;
import dev.guedes.gameoflife.views.cli.CLIViewManager;
import java.util.Scanner;

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

        bind(InputReader.class).in(Singleton.class);
        bind(OptionReader.class).in(Singleton.class);

        bind(CLIViewManager.class).in(Singleton.class);
        bind(CLIExitView.class).in(Singleton.class);

        Multibinder<View> viewMultibinder = Multibinder.newSetBinder(binder(), View.class);
        viewMultibinder.addBinding().to(CLIMainMenuView.class).in(Singleton.class);
        viewMultibinder.addBinding().to(CLIRulesView.class).in(Singleton.class);
        viewMultibinder.addBinding().to(CLIGameConfigView.class).in(Singleton.class);
    }

    @Provides
    @Singleton
    public Scanner scanner() { return new Scanner(System.in); }
}
