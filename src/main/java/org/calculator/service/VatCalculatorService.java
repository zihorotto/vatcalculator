package org.calculator.service;

import lombok.extern.slf4j.Slf4j;
import org.calculator.domain.VatCalculationDomain;
import org.calculator.dto.VatCalculationResultDto;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class VatCalculatorService {

    public VatCalculationResultDto calculate(VatCalculationDomain input) {
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

}

