package org.calculator.exceptionhandling;

import lombok.Getter;

@Getter
public class ValCalculationException extends RuntimeException {

    private final String message;

    public ValCalculationException(String message) {
        super(message);
        this.message = message;
    }

}
