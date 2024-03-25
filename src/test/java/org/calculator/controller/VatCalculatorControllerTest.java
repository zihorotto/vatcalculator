package org.calculator.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.calculator.domain.VatCalculationDomain;
import org.calculator.dto.VatCalculationResultDto;
import org.calculator.service.VatCalculatorService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
class VatCalculatorControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Mock
    private VatCalculatorService vatCalculatorService;

    @InjectMocks
    private VatCalculatorController vatCalculatorController;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void calculateVat_when_valid_input_returns_calculated_values() throws Exception {
        VatCalculationDomain request = new VatCalculationDomain(null, 120.0, null, 20.0);
        VatCalculationResultDto expectedResult = new VatCalculationResultDto(100.0, 120.0, 20.0);

        mockMvc.perform(MockMvcRequestBuilders.post("/api/vat/calculate")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().json(objectMapper.writeValueAsString(expectedResult)));
    }

    @Test
    void calculateVat_When_invalid_input_returns_badRequest() throws Exception {
        VatCalculationDomain request = new VatCalculationDomain(null, null, null, null);

        mockMvc.perform(MockMvcRequestBuilders.post("/api/vat/calculate")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }
}