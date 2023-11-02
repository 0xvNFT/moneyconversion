package com.vvv.moneyconversion.viewmodel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ConversionHistoryViewModel extends ViewModel {
    private final MutableLiveData<List<String>> conversionHistory = new MutableLiveData<>(new ArrayList<>());

    public MutableLiveData<List<String>> getConversionHistory() {
        return conversionHistory;
    }

    public void addConversion(String conversion) {
        List<String> currentHistory = new ArrayList<>(Objects.requireNonNull(conversionHistory.getValue()));
        currentHistory.add(conversion);
        conversionHistory.postValue(currentHistory);
    }


}

