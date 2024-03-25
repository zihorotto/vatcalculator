package org.calculator.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class VatCalculationResultDto {

    private Double net;
    private Double gross;
    private Double vatAmount;

}
