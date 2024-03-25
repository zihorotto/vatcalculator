package org.calculator.service;

import jakarta.validation.Validator;
import org.calculator.domain.VatCalculationDomain;
import org.calculator.dto.VatCalculationResultDto;
import org.calculator.exceptionhandling.VatCalculationException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.doThrow;

class VatCalculatorServiceTest {

    @Mock
    private Validator validator;

    @InjectMocks
    private VatCalculatorService vatCalculatorService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }


    @Test
    void calculate_when_valid_input_with_gross_returns_calculated_values() {
        VatCalculationDomain input = new VatCalculationDomain(null, 120.0, null, 20.0);
        VatCalculationResultDto expected = new VatCalculationResultDto(100.0, 120.0, 20.0);

        VatCalculationResultDto result = vatCalculatorService.calculate(input);

        assertEquals(expected, result);
    }

    @Test
    void calculate_WhenNetAndVatAmountProvided_ReturnsCalculatedValues() {
        VatCalculationDomain input = new VatCalculationDomain(100.0, null, 20.0, 20.0);
        VatCalculationResultDto expected = new VatCalculationResultDto(100.0, 120.0, 20.0);

        VatCalculationResultDto result = vatCalculatorService.calculate(input);

        assertEquals(expected, result);
    }

    @Test
    void calculate_when_Invalid_input_throws_exception() {
        VatCalculationDomain input = new VatCalculationDomain(100.0, 120.0, 20.0, 20.0);
        doThrow(VatCalculationException.class).when(validator).validate(input);

        assertThrows(VatCalculationException.class, () -> vatCalculatorService.calculate(input));
    }
}
