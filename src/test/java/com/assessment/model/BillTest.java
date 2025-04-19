package com.assessment.model;
import com.assessment.enums.ItemCategory;
import com.assessment.enums.UserType;
import com.assessment.model.Bill;
import com.assessment.model.Item;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class BillTest {

    @Test
    public void testBillRequestFields() {
        // Arrange
        Item item1 = new Item("Laptop", ItemCategory.ELECTRONICS, 1200.0);
        Item item2 = new Item("Apples", ItemCategory.GROCERY, 50.0);
        List<Item> itemList = Arrays.asList(item1, item2);

        UserType userType = UserType.CUSTOMER;
        int customerSince = 3;
        String originalCurrency = "USD";
        String targetCurrency = "PKR";

        Bill billRequest = new Bill();
        billRequest.setItems(itemList);
        billRequest.setUserType(UserType.CUSTOMER);
        billRequest.setCustomerTenureInYears(customerSince);
        billRequest.setOriginalCurrency(originalCurrency);
        billRequest.setTargetCurrency(targetCurrency);

        // Assert
        assertEquals(itemList, billRequest.getItems());
        assertEquals(userType, billRequest.getUserType());
        assertEquals(customerSince, billRequest.getCustomerTenureInYears());
        assertEquals(originalCurrency, billRequest.getOriginalCurrency());
        assertEquals(targetCurrency, billRequest.getTargetCurrency());
    }
}