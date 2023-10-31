package com.vvv.moneyconversion.ui.fragments;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.vvv.moneyconversion.BuildConfig;
import com.vvv.moneyconversion.R;
import com.vvv.moneyconversion.model.ExchangeResponse;
import com.vvv.moneyconversion.repository.CurrencyRepository;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LatestUpdatesFragmentApi extends Fragment {
    private final String[] currencies = {"USD", "EUR", "JPY", "GBP", "AUD", "CAD", "CHF", "CNY", "SEK", "NZD", "MXN", "SGD", "HKD", "NOK", "KRW"};
    private Spinner spinnerCurrency;
    private TextView tvCurrencyValueBuy;
    private TextView tvCurrencyValueSell;

    public LatestUpdatesFragmentApi() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_latest_updates, container, false);

        spinnerCurrency = view.findViewById(R.id.spinner_currency);
        tvCurrencyValueBuy = view.findViewById(R.id.tv_currency_value_buy);
        tvCurrencyValueSell = view.findViewById(R.id.tv_currency_value_sell);

        ArrayAdapter<String> adapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_spinner_item, currencies);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerCurrency.setAdapter(adapter);

        spinnerCurrency.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                updateCurrencyValues();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        return view;
    }

    private void updateCurrencyValues() {
        String selectedCurrency = spinnerCurrency.getSelectedItem().toString();
        Log.d("LatestUpdatesFragment", "API Key: " + BuildConfig.MY_API_KEY);

        CurrencyRepository.getInstance().getLatestRatesWithKey("USD", BuildConfig.MY_API_KEY).enqueue(new Callback<ExchangeResponse>() {
            @Override
            public void onResponse(@NonNull Call<ExchangeResponse> call, @NonNull Response<ExchangeResponse> response) {
                if (response.isSuccessful() && response.body() != null && response.body().getRates() != null) {
                    ExchangeResponse exchangeResponse = response.body();
                    float buyRate = 1.0f;
                    float sellRate = exchangeResponse.getRates().get(selectedCurrency);
                    tvCurrencyValueBuy.setText(String.format("Giá Mua: %.2f", buyRate));
                    tvCurrencyValueSell.setText(String.format("Giá Bán: %.2f", sellRate));
                } else {
                    Log.e("LatestUpdatesFragment", "Error in API response or rates map is null. Response: " + response);
                }
            }

            @Override
            public void onFailure(Call<ExchangeResponse> call, Throwable t) {
                Log.e("LatestUpdatesFragment", "API request failed", t);
            }
        });
    }

}

