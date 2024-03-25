package org.calculator.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class VatCalculationResultDto {

    @NotNull(message = "Net amount is required")
    @NotEmpty(message = "Net is can not empty")
    private Double net;

    @NotNull(message = "Gross amount is required")
    @NotEmpty(message = "Gross is can not empty")
    private Double gross;

    @NotNull(message = "VAT amount is required")
    @NotEmpty(message = "VAT Amount is can not empty")
    private Double vatAmount;

}
