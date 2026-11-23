package extensions;

import com.google.inject.Key;
import org.junit.jupiter.api.extension.BeforeEachCallback;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.ParameterContext;
import org.junit.jupiter.api.extension.ParameterResolver;

import dev.guedes.gameoflife.ApplicationInjector;

/*
 * Copyright (c) 2026 João Guedes
 * Project : Game of Life
 * File : GuiceJUnit5Extension.java
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
