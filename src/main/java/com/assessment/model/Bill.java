package com.assessment.model;

import com.assessment.enums.UserType;
import lombok.Data;

import java.util.List;


@Data
public class Bill {

    private List<Item> items;
    private UserType userType;
    private int customerTenureInYears;
    private String originalCurrency;
    private String targetCurrency;

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }

    public UserType getUserType() {
        return userType;
    }

    public void setUserType(UserType userType) {
        this.userType = userType;
    }

    public int getCustomerTenureInYears() {
        return customerTenureInYears;
    }

    public void setCustomerTenureInYears(int customerTenureInYears) {
        this.customerTenureInYears = customerTenureInYears;
    }

    public String getOriginalCurrency() {
        return originalCurrency;
    }

    public void setOriginalCurrency(String originalCurrency) {
        this.originalCurrency = originalCurrency;
    }

    public String getTargetCurrency() {
        return targetCurrency;
    }

    public void setTargetCurrency(String targetCurrency) {
        this.targetCurrency = targetCurrency;
    }
}

