package com.meldcx.appscheduler.utils

import android.view.View
import android.widget.AdapterView
import android.widget.Spinner
import android.widget.Toast
import androidx.appcompat.widget.AppCompatSpinner
import androidx.databinding.BindingAdapter
import androidx.databinding.InverseBindingAdapter
import androidx.databinding.InverseBindingListener
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

@BindingAdapter(value = ["setAdapterTest"], requireAll = false)
fun setAdapterTest(spinner: Spinner, projects: CurrencyData?) {
    projects?.let {
        spinner.adapter = it.list?.let { CurrencySpinnerAdapter(spinner.context, it) }
        spinner.setSelection(146)
        //setCurrentSelection(spinner, selectedUser)
        //setSpinnerListener(spinner, listener)
    }
}

@InverseBindingAdapter(attribute = "selectedUser")
fun getSelectedUser(spinner: Spinner): Rate {
    Toast.makeText(spinner.context, (spinner.selectedItem as Rate).readableRate, Toast.LENGTH_LONG).show()
    return spinner.selectedItem as Rate
}

private fun setSpinnerListener(spinner: Spinner, listener: InverseBindingListener) {
    spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
        override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) =
            listener.onChange()

        override fun onNothingSelected(adapterView: AdapterView<*>) = listener.onChange()
    }
}

private fun setCurrentSelection(spinner: Spinner, selectedItem: Rate?): Boolean {
    for (index in 0 until spinner.adapter.count) {
        if (spinner.getItemAtPosition(index) == selectedItem?.readableRate) {
            spinner.setSelection(index)
            return true
        }
    }
    return false
}

//
//@BindingAdapter(value = ["projects", "selectedProject", "selectedProjectAttrChanged"], requireAll = false)
//fun setProjects(spinner: Spinner, projects: List<Rate>, selectedProject: Rate, listener: InverseBindingListener) {
//    if (projects == null) return
//    spinner.adapter = MoodArrayAdapter(spinner.context, android.R.layout.activity_list_item, projects)
//    setCurrentSelection(spinner, selectedProject)
//    setSpinnerListener(spinner, listener)
//}

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

@BindingAdapter("clicks")
fun listenClicks(spinner: AppCompatSpinner, result: ObservableField<Rate>) {
    spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
        override fun onNothingSelected(parent: AdapterView<*>?) {

        }

        override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
            result.set(parent?.getItemAtPosition(position) as Rate)
        }
    }
}


