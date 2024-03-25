package org.calculator.service;

import jakarta.validation.Validator;
import lombok.extern.slf4j.Slf4j;
import org.calculator.domain.VatCalculationDomain;
import org.calculator.dto.VatCalculationResultDto;
import org.calculator.exceptionhandling.VatCalculationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class VatCalculatorService {
    private final Validator validator;

    @Autowired
    public VatCalculatorService(Validator validator) {
        this.validator = validator;
    }

    public VatCalculationResultDto calculate(VatCalculationDomain input) {
        validateInput(input);

        Double net = input.getNet();
        Double gross = input.getGross();
        Double vatAmount = input.getVatAmount();
        Double vatRate = input.getVatRate();

        if (net != null) {
            vatAmount = net * vatRate / 100;
            gross = net + vatAmount;
        } else if (gross != null) {
            net = gross / (1 + vatRate / 100);
            vatAmount = gross - net;
        } else {
            net = vatAmount / (vatRate / 100);
            gross = net + vatAmount;
        }

        return new VatCalculationResultDto(net, gross, vatAmount);
    }

    private void validateInput(VatCalculationDomain input) {
        if (!validator.validate(input).isEmpty()) {
            throw new VatCalculationException("Invalid input provided.");
        }

        if ((input.getNet() == null && input.getGross() == null && input.getVatAmount() == null) ||
                (input.getNet() != null && input.getGross() != null && input.getVatAmount() != null)) {
            throw new VatCalculationException("Please provide exactly one amount.");
        }

        if (input.getNet() != null && input.getNet() <= 0 ||
                input.getGross() != null && input.getGross() <= 0 ||
                input.getVatAmount() != null && input.getVatAmount() <= 0) {
            throw new VatCalculationException("Amounts must be positive.");
        }

        if (input.getVatRate() != null && !isValidVatRate(input.getVatRate())) {
            throw new VatCalculationException("Invalid VAT rate.");
        }
    }

    private boolean isValidVatRate(Double vatRate) {
        return vatRate == 10.0 || vatRate == 13.0 || vatRate == 20.0;
    }
}

