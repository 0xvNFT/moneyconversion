package com.vvv.moneyconversion.ui.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.vvv.moneyconversion.R;
import com.vvv.moneyconversion.ui.adapter.HistoryAdapter;
import com.vvv.moneyconversion.viewmodel.ConversionHistoryViewModel;

import java.util.ArrayList;

public class HistoryFragment extends Fragment {
    private ConversionHistoryViewModel historyViewModel;
    private RecyclerView historyRecyclerView;
    public HistoryFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_history, container, false);

        historyViewModel = new ViewModelProvider(requireActivity()).get(ConversionHistoryViewModel.class);
        historyRecyclerView = view.findViewById(R.id.historyRecyclerView);
        setupRecyclerView();
        return view;

    }

    private void setupRecyclerView() {
        historyRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        HistoryAdapter adapter = new HistoryAdapter(new ArrayList<>());
        historyRecyclerView.setAdapter(adapter);

        historyViewModel.getConversionHistory().observe(getViewLifecycleOwner(), conversions -> {
            adapter.updateData(conversions);
        });
    }
}

