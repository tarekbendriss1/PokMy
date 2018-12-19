package com.seemantov.pokmy.utils;

/**
 * Created by Tarek Ben Driss on 09/03/2018.
 */

public class ButtonPressedEvent {
    private final String message;

    public ButtonPressedEvent(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
