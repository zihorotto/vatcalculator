package org.calculator.exceptionhandling;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(VatCalculationException.class)
    public ResponseEntity<List<ValidationError>> handleVatCalculationException(VatCalculationException exception) {
        ValidationError validationError = new ValidationError("VAT Calculation",
                "Error occurred during VAT calculation: " + exception.getMessage());
        log.error("Error in VAT calculation: " + exception.getMessage());
        return new ResponseEntity<>(List.of(validationError), HttpStatus.BAD_REQUEST);
    }
}
