package com.assessment.service;


import com.assessment.dto.BillResponse;
import com.assessment.enums.ItemCategory;
import com.assessment.model.Bill;
import com.assessment.model.Item;
import org.springframework.stereotype.Service;


@Service
public class BillCalculationService {

    private final CurrencyExchangeClient exchangeClient;

    public BillCalculationService(CurrencyExchangeClient exchangeClient) {
        this.exchangeClient = exchangeClient;
    }

    public BillResponse calculateBillDetails(Bill bill) {
        double totalAmount = 0;
        double nonGroceryAmount = 0;

        for (Item item : bill.getItems()) {
            totalAmount += item.getPrice();
            if (item.getCategory() != ItemCategory.GROCERY) {
                nonGroceryAmount += item.getPrice();
            }
        }

        // Calculate discounts
        double discountRate = getDiscountRate(bill);
        double percentageDiscount = nonGroceryAmount * discountRate;
        double flatDiscount = Math.floor(totalAmount / 100) * 5;
        double totalDiscount = percentageDiscount + flatDiscount;

        // Calculate discounted amount
        double discountedAmount = totalAmount - totalDiscount;

        // Currency conversion
        double exchangeRate = exchangeClient.getExchangeRate(
                bill.getOriginalCurrency(),
                bill.getTargetCurrency()
        );
        double finalAmount = discountedAmount * exchangeRate;

        return new BillResponse(
                totalAmount,
                totalAmount * exchangeRate,
                totalDiscount,
                totalDiscount * exchangeRate,
                discountedAmount,
                discountedAmount * exchangeRate,
                bill.getTargetCurrency(),
                exchangeRate
        );
    }

    private double getDiscountRate(Bill bill) {
        return switch (bill.getUserType()) {
            case EMPLOYEE -> 0.30;
            case AFFILIATE -> 0.10;
            case CUSTOMER -> bill.getCustomerTenureInYears() > 2 ? 0.05 : 0.0;
        };
    }
}
