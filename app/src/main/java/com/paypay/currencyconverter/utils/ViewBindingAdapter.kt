package com.paypay.currencyconverter.utils

import android.view.View
import android.widget.AdapterView
import android.widget.Spinner
import androidx.appcompat.widget.AppCompatSpinner
import androidx.databinding.BindingAdapter
import androidx.databinding.ObservableField
import androidx.recyclerview.widget.RecyclerView
import com.paypay.currencyconverter.data.CurrencyResponse
import com.paypay.currencyconverter.data.ExchangeRate
import com.paypay.currencyconverter.ui.currency.CurrencyViewAdapter
import com.paypay.currencyconverter.ui.currency.CurrencyConverterSpinnerAdapter

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


