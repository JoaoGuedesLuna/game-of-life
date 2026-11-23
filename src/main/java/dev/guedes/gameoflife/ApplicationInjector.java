package dev.guedes.gameoflife;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.Key;
import lombok.Getter;

/*
 * Copyright (c) 2026 João Guedes
 * Project : Game of Life
 * File : ApplicationInjector.java
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
public class ApplicationInjector {
    @Getter
    private static final Injector injector = Guice.createInjector(new ApplicationModule());

    private ApplicationInjector() {}

    public static <T> T getInstance(Key<T> key) { return injector.getInstance(key); }

    public static <T> T getInstance(Class<T> clazz) { return injector.getInstance(clazz); }
}
