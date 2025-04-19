//package com.assessment.exceptions;
//
//
//import com.assessment.controller.BillCalculationController;
//import com.assessment.service.BillCalculationService;
//import com.assessment.service.CurrencyExchangeClient;
//import org.junit.jupiter.api.Test;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.http.MediaType;
//import org.springframework.test.web.servlet.MockMvc;
//
//import static org.mockito.Mockito.when;
//import static org.springframework.http.RequestEntity.post;
//import static org.springframework.test.web.client.match.MockRestRequestMatchers.content;
//import static org.springframework.test.web.client.match.MockRestRequestMatchers.jsonPath;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
//
//@WebMvcTest(controllers = BillCalculationController.class)
//public class GlobalExceptionHandlerTest {
//
//    @Autowired
//    private MockMvc mockMvc;
//
//    @MockBean
//    private BillCalculationService billCalculationService;
//
//    @MockBean
//    private CurrencyExchangeClient currencyExchangeClient;
//
//    @InjectMocks
//    private BillCalculationController billCalculationController;
//
//    @Test
//    public void testHandleCurrencyExchangeException() throws Exception {
//        when(currencyExchangeClient.getExchangeRate("USD", "INR")).thenThrow(new CurrencyExchangeException("Currency exchange error"));
//
//        mockMvc.perform(post("/api/calculate")
//                .contentType(MediaType.APPLICATION_JSON)
//                .content("{\"originalCurrency\":\"USD\",\"targetCurrency\":\"INR\"}"))
//                .andExpect(status().isInternalServerError())
//                .andExpect(jsonPath("$.message").value("Currency exchange error"));
//    }
//}
