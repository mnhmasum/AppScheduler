package com.meldcx.appscheduler.utils

import android.view.View
import android.widget.AdapterView
import android.widget.Spinner
import androidx.appcompat.widget.AppCompatSpinner
import androidx.databinding.BindingAdapter
import androidx.databinding.ObservableField
import androidx.recyclerview.widget.RecyclerView
import com.meldcx.appscheduler.data.Schedule
import com.meldcx.appscheduler.data.CurrencyData
import com.meldcx.appscheduler.data.Rate
import com.meldcx.appscheduler.ui.currency.CurrencyViewAdapter
import com.meldcx.appscheduler.ui.currency.CurrencySpinnerAdapter
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
fun setAdapterTest(spinner: Spinner, projects: CurrencyData?) {
    projects?.let {
        spinner.adapter = it.rateList?.let { CurrencySpinnerAdapter(spinner.context, it) }
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
fun submitCurrencyList(recyclerView: RecyclerView, currencyData: CurrencyData?) {
    val adapter = recyclerView.adapter as CurrencyViewAdapter?
    val rates = ArrayList<Rate>()
    currencyData?.let {
        rates.add(Rate("aed", it.getRates().aed))
        rates.add(Rate("afn", it.getRates().afn))
        rates.add(Rate("all", it.getRates().all))
        rates.add(Rate("amd", it.getRates().amd))
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
fun listenClicks(spinner: AppCompatSpinner, rate: ObservableField<Rate>) {
    spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
        override fun onNothingSelected(parent: AdapterView<*>?) {

        }

        override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
            rate.set(parent?.getItemAtPosition(position) as Rate)
        }
    }
}


