package extensions;

import com.google.inject.Key;
import dev.guedes.gameoflife.ApplicationInjector;
import org.junit.jupiter.api.extension.BeforeEachCallback;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.ParameterContext;
import org.junit.jupiter.api.extension.ParameterResolver;

/**
 * JUnit 5 extension for enabling Google Guice dependency injection in test classes.
 * <p>
 * This extension hooks into the JUnit 5 lifecycle and injects dependencies
 * into the test instance before each test method is executed. It ensures that
 * test classes can benefit from the same dependency injection configuration
 * used in the main application.
 * </p>
 *
 * @author João Guedes
 */
public class GuiceJUnit5Extension implements BeforeEachCallback, ParameterResolver {
    @Override
    public void beforeEach(ExtensionContext context) {
        Object testInstance = context.getRequiredTestInstance();
        ApplicationInjector.getInjector().injectMembers(testInstance);
    }

    @Override
    public boolean supportsParameter(ParameterContext parameterContext, ExtensionContext extensionContext) {
        return ApplicationInjector.getInjector().getBindings().containsKey(Key.get(parameterContext.getParameter().getType()));
    }

    @Override
    public Object resolveParameter(ParameterContext parameterContext, ExtensionContext extensionContext) {
        return ApplicationInjector.getInjector().getInstance(parameterContext.getParameter().getType());
    }
}
