package org.calculator.exceptionhandling;

import lombok.Getter;

@Getter
public class VatCalculationException extends RuntimeException {

    private final String message;

    public VatCalculationException(String message) {
        super(message);
        this.message = message;
    }

}
