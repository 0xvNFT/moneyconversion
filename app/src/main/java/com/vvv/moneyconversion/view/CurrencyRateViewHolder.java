package com.vvv.moneyconversion.view;

import android.view.View;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.vvv.moneyconversion.R;

public class CurrencyRateViewHolder extends RecyclerView.ViewHolder {
    public TextView currencyName;
    public TextView currencyCode;
    public TextView rate;
    public TextView currencyChangePercentage;

    public CurrencyRateViewHolder(View itemView) {
        super(itemView);
        currencyName = itemView.findViewById(R.id.currency_name);
        currencyCode = itemView.findViewById(R.id.currency_code);
        currencyChangePercentage = itemView.findViewById(R.id.currency_change_percentage);
        rate = itemView.findViewById(R.id.rate);
    }
}
