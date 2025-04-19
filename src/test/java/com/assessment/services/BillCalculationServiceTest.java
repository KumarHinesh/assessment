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


        when(exchangeClient.getExchangeRate("USD", "PKR")).thenReturn(280.0);

        BillResponse response = billCalculationService.calculateBillDetails(bill);


        double total = 1100.0;
        double nonGrocery = 1000.0;
        double percentageDiscount = 1000.0 * 0.30; // 300
        double flatDiscount = Math.floor(1100 / 100) * 5; // 55
        double totalDiscount = percentageDiscount + flatDiscount; // 355
        double discountedAmount = total - totalDiscount; // 745
        double finalAmount = discountedAmount * 80; // 59600

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

        when(exchangeClient.getExchangeRate("USD", "PKR")).thenReturn(280.0);

        BillResponse response = billCalculationService.calculateBillDetails(bill);

        double expectedDiscount = 1000.0 * 0.30 + 50.0; // $300 + $50
        assertEquals(expectedDiscount, response.getDiscountAppliedOrginalCurrency(), 0.01);

    }

}
