package com.vvv.moneyconversion.ui.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.vvv.moneyconversion.R;
import com.vvv.moneyconversion.data.CurrencyRate;

import java.util.ArrayList;
import java.util.List;

public class CurrencyRateAdapter extends RecyclerView.Adapter<CurrencyRateAdapter.ViewHolder> {
    private List<CurrencyRate> currencyRateList = new ArrayList<>();

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.currency_rate_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
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

    class ViewHolder extends RecyclerView.ViewHolder {
        TextView currencyName;
        TextView currencyCode;
        TextView rate;
        TextView currencyChangePercentage;

        ViewHolder(View itemView) {
            super(itemView);
            currencyName = itemView.findViewById(R.id.currency_name);
            currencyCode = itemView.findViewById(R.id.currency_code);
            currencyChangePercentage = itemView.findViewById(R.id.currency_change_percentage);
            rate = itemView.findViewById(R.id.rate);
        }
    }
}
