package org.calculator.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class VatCalculationDomain {
    private Double net;
    private Double gross;
    private Double vatAmount;
    private Double vatRate;
}
