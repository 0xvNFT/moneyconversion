package com.vvv.moneyconversion.api;

import com.vvv.moneyconversion.model.ExchangeResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface CurrencyApi {
    @GET("latest")
    Call<ExchangeResponse> getLatestRatesWithKey(@Query("base") String baseCurrency, @Query("access_key") String apiKey);
}
