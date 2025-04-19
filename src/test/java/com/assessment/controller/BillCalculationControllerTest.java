package com.assessment.controller;

import com.assessment.config.SecurityConfig;
import com.assessment.dto.BillResponse;

import com.assessment.enums.ItemCategory;
import com.assessment.enums.UserType;
import com.assessment.model.Bill;
import com.assessment.model.Item;
import com.assessment.service.BillCalculationService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;


@WebMvcTest(BillCalculationController.class)
@Import(SecurityConfig.class) // if you have a custom config that allows open access
@AutoConfigureMockMvc(addFilters = false) // disables Spring Security filters
class BillCalculationControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private BillCalculationService billCalculationService;

    @Autowired
    private ObjectMapper objectMapper;

    @TestConfiguration
    static class TestConfig {
        @Bean
        public BillCalculationService billCalculationService() {
            return Mockito.mock(BillCalculationService.class);
        }
    }

    @Test
    void testCalculatePayableAmount_success() throws Exception {
        // Arrange: Mock Bill
        Item item1 = new Item("Shampoo", ItemCategory.GROCERY, 40.0);
        Item item2 = new Item("Jeans", ItemCategory.CLOTHING, 60.0);

        Bill billRequest = new Bill();
        billRequest.setItems(List.of(item1, item2));
        billRequest.setUserType(UserType.EMPLOYEE);
        billRequest.setCustomerTenureInYears(3);
        billRequest.setOriginalCurrency("USD");
        billRequest.setTargetCurrency("EUR");

        // Arrange: Mock Response
        BillResponse mockResponse = new BillResponse();
        mockResponse.setTotalAmountOrginalCurrency(100.0);
        mockResponse.setTotalAmountTargetedCurrency(92.0);
        mockResponse.setDiscountAppliedOrginalCurrency(30.0);
        mockResponse.setDiscountAppliedTargetedCurrency(27.6);
        mockResponse.setFinalAmountAfterDiscountOriginalCurrency(70.0);
        mockResponse.setFinalAmountAfterDiscountTargetedCurrency(64.4);
        mockResponse.setTargetedCurrency("EUR");
        mockResponse.setExchangeRate(0.92);

        Mockito.when(billCalculationService.calculateBillDetails(any(Bill.class)))
                .thenReturn(mockResponse);

        // Act & Assert
        mockMvc.perform(MockMvcRequestBuilders.post("/api/calculate")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(billRequest)))
                .andExpect(status().isOk());
    }
}
