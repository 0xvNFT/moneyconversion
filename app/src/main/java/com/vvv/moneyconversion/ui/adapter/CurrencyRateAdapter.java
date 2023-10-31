package com.vvv.moneyconversion.ui.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;

import com.vvv.moneyconversion.R;
import com.vvv.moneyconversion.data.CurrencyRate;

import java.util.ArrayList;
import java.util.List;

public class CurrencyRateAdapter extends RecyclerView.Adapter<CurrencyRateViewHolder> {
    private List<CurrencyRate> currencyRateList = new ArrayList<>();

    @Override
    public CurrencyRateViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.currency_rate_item, parent, false);
        return new CurrencyRateViewHolder(view);
    }

    @Override
    public void onBindViewHolder(CurrencyRateViewHolder holder, int position) {
        CurrencyRate data = currencyRateList.get(position);
        holder.currencyName.setText(data.currencyName);
        holder.currencyCode.setText(data.currencyCode);
        holder.currencyChangePercentage.setText(String.format("+%.2f%%", data.getChangePercentage()));
        holder.rate.setText(String.format("%.2f", data.rate));
    }

    @Override
    public int getItemCount() {
        return currencyRateList.size();
    }

    public void setData(List<CurrencyRate> data) {
        this.currencyRateList = data;
        notifyDataSetChanged();
    }
}
