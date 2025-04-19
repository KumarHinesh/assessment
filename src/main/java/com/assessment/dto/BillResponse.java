package com.assessment.dto;


import lombok.NoArgsConstructor;

@NoArgsConstructor
public class BillResponse {

    private double totalAmountOrginalCurrency;
    private double totalAmountTargetedCurrency;

    private double discountAppliedOrginalCurrency;
    private double discountAppliedTargetedCurrency;
    private double finalAmountAfterDiscountOriginalCurrency;
    private double finalAmountAfterDiscountTargetedCurrency;
    private String targetedCurrency;
    private double exchangeRate;



    // constructor
    public BillResponse(double totalAmountOrginalCurrency, double totalAmountTargetedCurrency,
                        double discountAppliedOrginalCurrency, double discountAppliedTargetedCurrency,
                        double finalAmountAfterDiscountOriginalCurrency, double finalAmountAfterDiscountTargetedCurrency,
                        String currency, double exchangeRate) {
        this.totalAmountOrginalCurrency = totalAmountOrginalCurrency;
        this.totalAmountTargetedCurrency = totalAmountTargetedCurrency;
        this.discountAppliedOrginalCurrency = discountAppliedOrginalCurrency;
        this.discountAppliedTargetedCurrency = discountAppliedTargetedCurrency;
        this.finalAmountAfterDiscountOriginalCurrency = finalAmountAfterDiscountOriginalCurrency;
        this.finalAmountAfterDiscountTargetedCurrency = finalAmountAfterDiscountTargetedCurrency;
        this.targetedCurrency = currency;
        this.exchangeRate = exchangeRate;
    }

    // cerate setter and getter methods for all the variables
    public double getTotalAmountOrginalCurrency() {
        return totalAmountOrginalCurrency;
    }
    public void setTotalAmountOrginalCurrency(double totalAmountOrginalCurrency) {
        this.totalAmountOrginalCurrency = totalAmountOrginalCurrency;
    }
    public double getTotalAmountTargetedCurrency() {
        return totalAmountTargetedCurrency;
    }
    public void setTotalAmountTargetedCurrency(double totalAmountTargetedCurrency) {
        this.totalAmountTargetedCurrency = totalAmountTargetedCurrency;
    }
    public double getDiscountAppliedOrginalCurrency() {
        return discountAppliedOrginalCurrency;
    }
    public void setDiscountAppliedOrginalCurrency(double discountAppliedOrginalCurrency) {
        this.discountAppliedOrginalCurrency = discountAppliedOrginalCurrency;
    }
    public double getDiscountAppliedTargetedCurrency() {
        return discountAppliedTargetedCurrency;
    }
    public void setDiscountAppliedTargetedCurrency(double discountAppliedTargetedCurrency) {
        this.discountAppliedTargetedCurrency = discountAppliedTargetedCurrency;
    }
    public double getFinalAmountAfterDiscountOriginalCurrency() {
        return finalAmountAfterDiscountOriginalCurrency;
    }
    public void setFinalAmountAfterDiscountOriginalCurrency(double finalAmountAfterDiscountOriginalCurrency) {
        this.finalAmountAfterDiscountOriginalCurrency = finalAmountAfterDiscountOriginalCurrency;
    }
    public double getFinalAmountAfterDiscountTargetedCurrency() {
        return finalAmountAfterDiscountTargetedCurrency;
    }
    public void setFinalAmountAfterDiscountTargetedCurrency(double finalAmountAfterDiscountTargetedCurrency) {
        this.finalAmountAfterDiscountTargetedCurrency = finalAmountAfterDiscountTargetedCurrency;
    }
    public String getTargetedCurrency() {
        return targetedCurrency;
    }
    public void setTargetedCurrency(String targetedCurrency) {
        this.targetedCurrency = targetedCurrency;
    }
    public double getExchangeRate() {
        return exchangeRate;
    }
    public void setExchangeRate(double exchangeRate) {
        this.exchangeRate = exchangeRate;
    }

}

