package com.meldcx.appscheduler.utils

import android.view.View
import android.widget.AdapterView
import android.widget.Spinner
import androidx.appcompat.widget.AppCompatSpinner
import androidx.databinding.BindingAdapter
import androidx.databinding.ObservableField
import androidx.recyclerview.widget.RecyclerView
import com.meldcx.appscheduler.data.Schedule
import com.meldcx.appscheduler.data.CurrencyResponse
import com.meldcx.appscheduler.data.ExchangeRate
import com.meldcx.appscheduler.ui.currency.CurrencyViewAdapter
import com.meldcx.appscheduler.ui.currency.CurrencyConverterSpinnerAdapter
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

@BindingAdapter(value = ["setCurrencyList"], requireAll = false)
fun setAdapterTest(spinner: Spinner, projects: CurrencyResponse?) {
    projects?.let {
        spinner.adapter = it.exchangeRateList?.let { CurrencyConverterSpinnerAdapter(spinner.context, it) }
        spinner.setSelection(146)
        //setCurrentSelection(spinner, selectedUser)
        //setSpinnerListener(spinner, listener)
    }
}

@BindingAdapter("submitList")
fun submitList(recyclerView: RecyclerView, schedules: List<Schedule>?) {
    val adapter = recyclerView.adapter as MainViewAdapter?
    adapter?.setAlarms(schedules ?: listOf())
}

@BindingAdapter("submitCurrencyList")
fun submitCurrencyList(recyclerView: RecyclerView, currencyData: CurrencyResponse?) {
    val adapter = recyclerView.adapter as CurrencyViewAdapter?
    val rates = ArrayList<ExchangeRate>()
    currencyData?.let {
        rates.add(ExchangeRate("aed", it.getRates().aed))
        rates.add(ExchangeRate("afn", it.getRates().afn))
        rates.add(ExchangeRate("all", it.getRates().all))
        rates.add(ExchangeRate("amd", it.getRates().amd))
        //adapter?.setCurrencyList(rates ?: listOf())
    }
}

@BindingAdapter("android:onLongClick")
fun setOnLongClickListener(view: View, func: () -> Unit) {
    view.setOnLongClickListener {
        func()
        return@setOnLongClickListener true
    }
}

@BindingAdapter("changeListener")
fun listenClicks(spinner: AppCompatSpinner, exchangeRate: ObservableField<ExchangeRate>) {
    spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
        override fun onNothingSelected(parent: AdapterView<*>?) {

        }

        override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
            exchangeRate.set(parent?.getItemAtPosition(position) as ExchangeRate)
        }
    }
}


