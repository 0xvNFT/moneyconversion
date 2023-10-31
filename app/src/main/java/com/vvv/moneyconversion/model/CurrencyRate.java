package com.vvv.moneyconversion.data;

public class CurrencyRate {
    public String currencyName;
    public String currencyCode;
    public float rate;
    private float changePercentage;

    public CurrencyRate(String currencyName, String currencyCode, float rate) {
        this.currencyName = currencyName;
        this.currencyCode = currencyCode;
        this.rate = rate;
    }

    public float getChangePercentage() {
        return changePercentage;
    }

    public void setChangePercentage(float changePercentage) {
        this.changePercentage = changePercentage;
    }

}
