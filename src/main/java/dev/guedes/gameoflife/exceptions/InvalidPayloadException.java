package dev.guedes.gameoflife.exceptions;

import dev.guedes.gameoflife.enums.ViewAction;

/**
 * Thrown when a view transition occurs but the provided payload
 * does not match the required type for the target action.
 *
 * @author João Guedes
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
