package com.vvv.moneyconversion.repository;

import com.vvv.moneyconversion.api.CurrencyApi;
import com.vvv.moneyconversion.model.ExchangeResponse;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class CurrencyRepository {

    private static final String BASE_URL = "http://api.currencylayer.com/";
    private static CurrencyRepository instance;
    private final CurrencyApi currencyApi;

    private CurrencyRepository() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        currencyApi = retrofit.create(CurrencyApi.class);
    }

    public static synchronized CurrencyRepository getInstance() {
        if (instance == null) {
            instance = new CurrencyRepository();
        }
        return instance;
    }

    public Call<ExchangeResponse> getLatestRatesWithKey(String baseCurrency, String apiKey) {
        return currencyApi.getLatestRatesWithKey(baseCurrency, apiKey);
    }
}
