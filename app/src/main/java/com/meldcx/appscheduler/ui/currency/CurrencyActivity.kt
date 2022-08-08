package com.meldcx.appscheduler.ui.currency

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.core.view.isVisible
import androidx.databinding.Observable
import androidx.databinding.ObservableField
import androidx.lifecycle.lifecycleScope
import com.meldcx.appscheduler.R
import com.meldcx.appscheduler.broadcastreceiver.AlarmBroadcastReceiver
import com.meldcx.appscheduler.data.AppState
import com.meldcx.appscheduler.data.ConverterIntent
import com.meldcx.appscheduler.data.Rate
import com.meldcx.appscheduler.databinding.ActivityCurrencyConvertBinding
import com.meldcx.appscheduler.dependencyinjection.MainActivityComponent
import com.meldcx.appscheduler.ui.base.BaseActivity
import com.meldcx.appscheduler.utils.enableIntervalAPICallAlarmService
import kotlinx.android.synthetic.main.activity_app_list.*
import kotlinx.android.synthetic.main.activity_app_list.progressBar
import kotlinx.android.synthetic.main.activity_currency_convert.*
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

class CurrencyActivity : BaseActivity<ActivityCurrencyConvertBinding>() {
    @Inject
    lateinit var currencyViewModel: CurrencyViewModel

    @Inject
    lateinit var currencyViewAdapter: CurrencyViewAdapter

    override fun performDependencyInjection(activityComponent: MainActivityComponent) {
        activityComponent.inject(this)
    }

    override fun getLayoutResource(): Int {
        return R.layout.activity_currency_convert
    }

    @RequiresApi(Build.VERSION_CODES.M)
    override fun initComponents() {
        binding.apply {
            viewModel = currencyViewModel
            adapter = currencyViewAdapter
            activity = this@CurrencyActivity
        }

        lifecycleScope.launch {
            launch {
                currencyViewModel.intent.send(ConverterIntent.FetchData)
            }
            launch {
                currencyViewModel.dataState.collect { render(it) }
            }
        }
    }

    private fun render(it: AppState) {
        progressBar.isVisible = it is AppState.Loading
        when (it) {
            is AppState.Success -> updateRateList(it.data?.list)
            is AppState.CompletedConversion -> updateRateList(it.data)
            is AppState.Error -> toast(it.error)
        }
    }

    private fun updateRateList(it: List<Rate>?) {
        currencyViewAdapter.setCurrencyList(it)
    }

    val changeOfRate = ObservableField<Rate>().apply {
        addOnPropertyChangedCallback(object : Observable.OnPropertyChangedCallback() {
            override fun onPropertyChanged(sender: Observable?, propertyId: Int) {
                this@apply.get()?.let { inputSendToConverter() }
            }
        })
    }

    private fun inputSendToConverter() {
        val inputAmount: Double = getInput()
        lifecycleScope.launch {
            val startConversion = ConverterIntent.StartConversion(inputAmount)
            currencyViewModel.intent.send(startConversion)
        }
    }

    private fun getInput() =
        if (!editTextNumber.text.isNullOrEmpty()) editTextNumber.text.toString().toDouble() else 0.0
}