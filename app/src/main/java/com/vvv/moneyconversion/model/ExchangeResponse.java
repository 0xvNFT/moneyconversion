package com.vvv.moneyconversion.model;

import java.util.Map;

public class ExchangeResponse {
    private Map<String, Float> rates;

    public Map<String, Float> getRates() {
        return rates;
    }

    public void setRates(Map<String, Float> rates) {
        this.rates = rates;
    }
}

