package com.meldcx.appscheduler.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.meldcx.appscheduler.data.Rate;

import java.util.List;

import kotlin.collections.ArrayDeque;

public class ConverterUtil {
    Context context;

    public ConverterUtil(Context context) {
        this.context = context;
    }

    public static List<Rate> convertValueAndGet(double inputValue, List<Rate> rateList) {
        List<Rate> updatedRateList = new ArrayDeque<>();
        for (Rate rate : rateList) {
            double result = inputValue * rate.getRate();
            Rate newRate = new Rate(rate.getCurrencyName(), result);
            updatedRateList.add(newRate);
        }
        return updatedRateList;
    }

    public boolean isNetworkAvailable() {
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = cm.getActiveNetworkInfo();
        return networkInfo != null && networkInfo.isConnected();
    }
}