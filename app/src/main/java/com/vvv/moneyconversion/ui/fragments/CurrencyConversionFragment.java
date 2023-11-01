package com.vvv.moneyconversion.ui.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.vvv.moneyconversion.R;

import java.util.HashMap;
import java.util.Map;

public class CurrencyConversionFragment extends Fragment {

    TextView targetAmount;
    private final String[] currencies = {"USD", "VND"};
    private final Map<String, Double> conversionRates = new HashMap<>();
    private LinearLayout sourceFlagsContainer, targetFlagsContainer;
    private TextView sourceCurrencyName, targetCurrencyName;
    private EditText sourceAmount;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_currency_conversion, container, false);

        // Initialize the conversion rates
        conversionRates.put("USDVND", 23000.0);
        conversionRates.put("VNDUSD", 1 / 23000.0); // The reverse conversion

        sourceFlagsContainer = view.findViewById(R.id.sourceFlagsContainer);
        targetFlagsContainer = view.findViewById(R.id.targetFlagsContainer);
        sourceCurrencyName = view.findViewById(R.id.sourceCurrencyName);
        targetCurrencyName = view.findViewById(R.id.targetCurrencyName);
        sourceAmount = view.findViewById(R.id.sourceAmount);
        targetAmount = view.findViewById(R.id.targetAmount);

        targetCurrencyName.setText(currencies[1]); // Set initial target currency name

        for (String currency : currencies) {
            ImageView sourceFlagIcon = new ImageView(getContext());
            int resId = getResources().getIdentifier(currency.toLowerCase() + "_flag", "drawable", getActivity().getPackageName());
            sourceFlagIcon.setImageResource(resId);
            sourceFlagIcon.setTag(currency);
            sourceFlagIcon.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    changeCurrency(true, view.getTag().toString());
                }
            });
            configureFlag(sourceFlagIcon, sourceFlagsContainer);

            ImageView targetFlagIcon = new ImageView(getContext());
            targetFlagIcon.setImageResource(resId);
            targetFlagIcon.setTag(currency);
            targetFlagIcon.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    changeCurrency(false, view.getTag().toString());
                }
            });
            configureFlag(targetFlagIcon, targetFlagsContainer);
        }

        view.findViewById(R.id.convertButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                convertCurrency();
            }
        });

        return view;
    }

    private void configureFlag(ImageView flagIcon, LinearLayout container) {
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(100, 100);  // width, height in pixels
        params.setMargins(10, 10, 10, 10);  // set margins if you want spacing between flags
        flagIcon.setLayoutParams(params);
        container.addView(flagIcon);
    }

    private void changeCurrency(boolean isSource, String currency) {
        if (isSource) {
            sourceCurrencyName.setText(currency);
            targetCurrencyName.setText(currencies[0].equals(currency) ? currencies[1] : currencies[0]);
        } else {
            targetCurrencyName.setText(currency);
            sourceCurrencyName.setText(currencies[0].equals(currency) ? currencies[1] : currencies[0]);
        }
        convertCurrency();
    }

    private void convertCurrency() {
        String sourceCurrency = sourceCurrencyName.getText().toString();
        String targetCurrency = targetCurrencyName.getText().toString();

        Double rate = conversionRates.get(sourceCurrency + targetCurrency);

        if (rate == null) {
            rate = 1 / conversionRates.get(targetCurrency + sourceCurrency);
        }

        if (rate != null) {
            try {
                double amount = Double.parseDouble(sourceAmount.getText().toString());
                double convertedAmount = amount * rate;
                targetAmount.setText(String.format("%.2f", convertedAmount));
            } catch (NumberFormatException e) {
                sourceAmount.setError("Invalid input!");
            }
        } else {
            targetAmount.setError("Conversion rate not available!");
        }
    }
}
