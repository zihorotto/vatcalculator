package org.calculator.controller;


import jakarta.validation.Valid;
import org.calculator.domain.VatCalculationDomain;
import org.calculator.dto.VatCalculationResultDto;
import org.calculator.service.VatCalculatorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/vat")
public class VatCalculatorController {

    private final VatCalculatorService vatCalculatorService;

    @Autowired
    public VatCalculatorController(VatCalculatorService vatCalculatorService) {
        this.vatCalculatorService = vatCalculatorService;
    }

    @PostMapping("/calculate")
    public ResponseEntity<VatCalculationResultDto> calculateVat(@Valid @RequestBody VatCalculationDomain input) {
        VatCalculationResultDto result = vatCalculatorService.calculate(input);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}
