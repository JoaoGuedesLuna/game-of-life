package dev.guedes.gameoflife.exceptions;

import dev.guedes.gameoflife.enums.ViewAction;

/*
 * Copyright (c) 2026 João Guedes
 * Project : Game of Life
 * File : InvalidPayloadException.java
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
public class InvalidPayloadException extends RuntimeException {
    public InvalidPayloadException(ViewAction action, Class<?> expectedType, Object actualPayload) {
        super(String.format(
                "Invalid payload for action [%s]: Expected %s but got %s",
                action,
                expectedType.getSimpleName(),
                (actualPayload != null ? actualPayload.getClass().getSimpleName() : "null")
        ));
    }
}
