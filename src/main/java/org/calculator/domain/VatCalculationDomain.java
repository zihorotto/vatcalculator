package org.calculator.domain;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class VatCalculationDomain {

    @NotNull(message = "Net amount is required")
    @NotEmpty(message = "Net is can not empty")
    private Double net;

    @NotNull(message = "Gross amount is required")
    @NotEmpty(message = "Gross is can not empty")
    private Double gross;

    @NotNull(message = "VAT amount is required")
    @NotEmpty(message = "VAT Amount is can not empty")
    private Double vatAmount;

    @NotNull(message = "VAT rate is required")
    @NotEmpty(message = "VAT rate is can not empty")
    private Double vatRate;
}
