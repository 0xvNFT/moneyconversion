package com.vvv.moneyconversion.ui.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.vvv.moneyconversion.R;
import com.vvv.moneyconversion.data.CurrencyRate;
import com.vvv.moneyconversion.ui.adapter.CurrencyRateAdapter;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class LatestUpdatesFragment extends Fragment {

    private final String[] currencies = {"USD", "EUR", "JPY", "GBP", "AUD", "CAD", "CHF", "CNY", "SEK", "NZD", "MXN", "SGD", "HKD", "NOK", "KRW"};
    private final Random random = new Random();
    private Spinner spinnerCurrency;
    private TextView tvCurrencyValueBuy;
    private TextView tvCurrencyValueSell;
    private RecyclerView recyclerView;
    private CurrencyRateAdapter currencyRateAdapter;

    public LatestUpdatesFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_latest_updates, container, false);

        spinnerCurrency = view.findViewById(R.id.spinner_currency);
        tvCurrencyValueBuy = view.findViewById(R.id.tv_currency_value_buy);
        tvCurrencyValueSell = view.findViewById(R.id.tv_currency_value_sell);
        recyclerView = view.findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

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
                // No action needed
            }
        });

        currencyRateAdapter = new CurrencyRateAdapter();
        recyclerView.setAdapter(currencyRateAdapter);
        generateCurrencyRateData();

        return view;
    }

    private void generateCurrencyRateData() {
        List<CurrencyRate> currencyRateList = new ArrayList<>();
        for (String currency : currencies) {
            float rate = 20 + random.nextFloat() * 10;
            float percentageChange = random.nextFloat() * 5;
            CurrencyRate currencyRate = new CurrencyRate(currency, currency, rate);
            currencyRate.setChangePercentage(percentageChange);
            currencyRateList.add(currencyRate);
        }
        currencyRateAdapter.setData(currencyRateList);
    }


    private void updateCurrencyValues() {
        float buyRate = 20 + random.nextFloat() * 10;
        float sellRate = buyRate + random.nextFloat() * 2;

        tvCurrencyValueBuy.setText(String.format("Giá Mua: %.2f", buyRate));
        tvCurrencyValueSell.setText(String.format("Giá Bán: %.2f", sellRate));
    }
}
