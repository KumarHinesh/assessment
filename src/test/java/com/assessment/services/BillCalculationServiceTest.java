package com.assessment.services;

import com.assessment.dto.BillResponse;
import com.assessment.enums.ItemCategory;
import com.assessment.enums.UserType;
import com.assessment.model.Bill;
import com.assessment.model.Item;
import com.assessment.service.BillCalculationService;
import com.assessment.service.CurrencyExchangeClient;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.mockito.Mockito;

import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.when;
import static org.skyscreamer.jsonassert.JSONAssert.assertEquals;

public class BillCalculationServiceTest {

    private CurrencyExchangeClient exchangeClient;
    private BillCalculationService billCalculationService;

    @BeforeEach
    void setup() {
        exchangeClient = Mockito.mock(CurrencyExchangeClient.class);
        billCalculationService = new BillCalculationService(exchangeClient);
    }

    @Test
    void testCalculateBillDetails_forEmployee() {
        Bill bill = new Bill();
        bill.setUserType(UserType.EMPLOYEE);
        bill.setOriginalCurrency("USD");
        bill.setTargetCurrency("PKR");
        bill.setCustomerTenureInYears(1);
        bill.setItems(List.of(
                new Item("Laptop", ItemCategory.ELECTRONICS, 1000.0),
                new Item("Apples", ItemCategory.GROCERY, 100.0)
        ));


        when(exchangeClient.getExchangeRate("USD", "PKR")).thenReturn(Optional.of(280.0));

        BillResponse response = billCalculationService.calculateBillDetails(bill);



        assertEquals("PKR", response.getTargetedCurrency());


    }

    @Test
    public void testEmployeeGets30PercentDiscount() {
        Bill bill = new Bill();
        bill.setUserType(UserType.EMPLOYEE);
        bill.setCustomerTenureInYears(0); // Not relevant for EMPLOYEE
        bill.setOriginalCurrency("USD");
        bill.setTargetCurrency("PKR");
        bill.setItems(List.of(
                new Item("Laptop", ItemCategory.ELECTRONICS, 1000.0)
        ));

        when(exchangeClient.getExchangeRate("USD", "PKR")).thenReturn(Optional.of(280.0));

        BillResponse response = billCalculationService.calculateBillDetails(bill);

        double expectedDiscount = 1000.0 * 0.30 + 50.0; // $300 + $50
        assertEquals(expectedDiscount, response.getDiscountAppliedOrginalCurrency(), 0.01);

    }

}
