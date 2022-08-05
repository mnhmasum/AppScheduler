package com.meldcx.appscheduler.utils

import android.view.View
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.meldcx.appscheduler.data.Schedule
import com.meldcx.appscheduler.retrofit.CurrencyData
import com.meldcx.appscheduler.retrofit.Rate
import com.meldcx.appscheduler.ui.currency.CurrencyViewAdapter
import com.meldcx.appscheduler.ui.main.MainViewAdapter

@BindingAdapter("setAdapter")
fun setAdapter(
    recyclerView: RecyclerView,
    adapter: RecyclerView.Adapter<RecyclerView.ViewHolder>?
) {
    adapter?.let {
        recyclerView.adapter = it
    }
}

@BindingAdapter("submitList")
fun submitList(recyclerView: RecyclerView, schedules: List<Schedule>?) {
    val adapter = recyclerView.adapter as MainViewAdapter?
    adapter?.setAlarms(schedules ?: listOf())
}

@BindingAdapter("submitCurrencyList")
fun submitCurrencyList(recyclerView: RecyclerView, currencyData: CurrencyData?) {
    val adapter = recyclerView.adapter as CurrencyViewAdapter?
    val rates = ArrayList<Rate>()
    currencyData?.let {
        rates.add(Rate("aed", it.getRates().aed))
        rates.add(Rate("afn", it.getRates().afn))
        rates.add(Rate("all", it.getRates().all))
        rates.add(Rate("amd", it.getRates().amd))
        adapter?.setCurrencyList(rates ?: listOf())
    }
}

@BindingAdapter("android:onLongClick")
fun setOnLongClickListener(view: View, func: () -> Unit) {
    view.setOnLongClickListener {
        func()
        return@setOnLongClickListener true
    }
}
