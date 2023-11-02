package com.vvv.moneyconversion.ui.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.vvv.moneyconversion.R;

import java.util.ArrayList;
import java.util.List;

public class HistoryAdapter extends RecyclerView.Adapter<HistoryAdapter.HistoryViewHolder> {
    private List<String> data;

    public HistoryAdapter(List<String> data) {
        this.data = data;
    }

    @NonNull
    @Override
    public HistoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_history, parent, false);
        return new HistoryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HistoryViewHolder holder, int position) {
        // Assume data format: "15h00 - 13/10/2023,USD,VND,200,4.891.000,00"
        String[] parts = data.get(position).split(",");
        holder.txtDate.setText(parts[0]);
        holder.imgSourceFlag.setImageResource(getFlagResource(parts[1]));
        holder.imgTargetFlag.setImageResource(getFlagResource(parts[2]));
        holder.txtConversion.setText(parts[1] + " -> " + parts[2] + ": " + parts[3] + " -> " + parts[4]);
        // Add delete functionality if needed
    }

    private int getFlagResource(String currencyCode) {
        switch (currencyCode) {
            case "USD":
                return R.drawable.usd_flag;
            case "VND":
                return R.drawable.vnd_flag;
            default:
                return 0;  // Add default flag or error handling
        }
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public void updateData(List<String> newData) {
        this.data = new ArrayList<>(newData);
        notifyDataSetChanged();
    }


    public static class HistoryViewHolder extends RecyclerView.ViewHolder {
        TextView txtDate, txtConversion;
        ImageView imgSourceFlag, imgTargetFlag;

        public HistoryViewHolder(@NonNull View itemView) {
            super(itemView);
            txtDate = itemView.findViewById(R.id.txtDate);
            txtConversion = itemView.findViewById(R.id.txtConversion);
            imgSourceFlag = itemView.findViewById(R.id.imgSourceFlag);
            imgTargetFlag = itemView.findViewById(R.id.imgTargetFlag);
        }
    }
}

