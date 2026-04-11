package dev.guedes.gameoflife;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.inject.Singleton;
import com.google.inject.assistedinject.FactoryModuleBuilder;
import com.google.inject.multibindings.Multibinder;
import com.google.inject.name.Names;
import dev.guedes.gameoflife.mappers.GridMapper;
import dev.guedes.gameoflife.mappers.impl.GridMapperImpl;
import dev.guedes.gameoflife.utils.cli.InputReader;
import dev.guedes.gameoflife.utils.cli.OptionReader;
import dev.guedes.gameoflife.validators.BoundedNumberValidator;
import dev.guedes.gameoflife.validators.BoundedNumberValidatorFactory;
import dev.guedes.gameoflife.validators.PopulationValidator;
import dev.guedes.gameoflife.validators.PopulationValidatorFactory;
import dev.guedes.gameoflife.views.View;
import dev.guedes.gameoflife.views.ViewManager;
import dev.guedes.gameoflife.views.cli.CLIExitView;
import dev.guedes.gameoflife.views.cli.CLIGameConfigView;
import dev.guedes.gameoflife.views.cli.CLIGameGridView;
import dev.guedes.gameoflife.views.cli.CLIGameGridViewFactory;
import dev.guedes.gameoflife.views.cli.CLIMainMenuView;
import dev.guedes.gameoflife.views.cli.CLIExplanationView;
import dev.guedes.gameoflife.views.cli.CLIViewManager;
import dev.guedes.gameoflife.views.gui.GUIMainView;
import dev.guedes.gameoflife.views.gui.SwingViewManager;
import dev.guedes.gameoflife.views.gui.components.footer.Footer;
import dev.guedes.gameoflife.views.gui.components.grid.GridPanel;
import dev.guedes.gameoflife.views.gui.components.header.Header;
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

        install(new FactoryModuleBuilder()
                .implement(CLIGameGridView.class, CLIGameGridView.class)
                .build(CLIGameGridViewFactory.class));

        bind(GridMapper.class).to(GridMapperImpl.class).in(Singleton.class);

        bind(InputReader.class).in(Singleton.class);
        bind(OptionReader.class).in(Singleton.class);

        bind(ViewManager.class).annotatedWith(Names.named("CLI")).to(CLIViewManager.class).in(Singleton.class);

        Multibinder<View> viewMultibinder = Multibinder.newSetBinder(binder(), View.class);
        viewMultibinder.addBinding().to(CLIMainMenuView.class).in(Singleton.class);
        viewMultibinder.addBinding().to(CLIExplanationView.class).in(Singleton.class);
        viewMultibinder.addBinding().to(CLIGameConfigView.class).in(Singleton.class);
        viewMultibinder.addBinding().to(CLIExitView.class).in(Singleton.class);

        bind(ViewManager.class).annotatedWith(Names.named("Swing")).to(SwingViewManager.class).in(Singleton.class);
        bind(GUIMainView.class).in(Singleton.class);
        bind(Header.class).in(Singleton.class);
        bind(GridPanel.class).in(Singleton.class);
        bind(Footer.class).in(Singleton.class);
    }

    @Provides
    @Singleton
    public Scanner scanner() { return new Scanner(System.in); }
}
