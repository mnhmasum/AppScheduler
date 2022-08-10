package com.meldcx.appscheduler.ui.currency

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.core.view.isVisible
import androidx.databinding.Observable
import androidx.databinding.ObservableField
import androidx.lifecycle.lifecycleScope
import com.meldcx.appscheduler.R
import com.meldcx.appscheduler.data.DataState
import com.meldcx.appscheduler.data.DataState.*
import com.meldcx.appscheduler.data.ConverterIntent
import com.meldcx.appscheduler.data.CurrencyResponse
import com.meldcx.appscheduler.data.ExchangeRate
import com.meldcx.appscheduler.databinding.ActivityCurrencyConvertBinding
import com.meldcx.appscheduler.dependencyinjection.MainActivityComponent
import com.meldcx.appscheduler.ui.base.BaseActivity
import kotlinx.android.synthetic.main.activity_currency_convert.*
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

class CurrencyConverterActivity : BaseActivity<ActivityCurrencyConvertBinding>() {
    @Inject
    lateinit var currencyConverterViewModel: CurrencyConverterViewModel

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
            viewModel = currencyConverterViewModel
            adapter = currencyViewAdapter
            activity = this@CurrencyConverterActivity
        }

        lifecycleScope.launch {
            launch {
                currencyConverterViewModel.intentAction.send(ConverterIntent.Fetch)
            }
            launch {
                currencyConverterViewModel.dataState.collect { render(it) }
            }
        }
    }

    private fun render(it: DataState) {
        progressBar.isVisible = it is Loading
        when (it) {
            is Success -> updateBaseRateSpinner(it.data)
            is ConversionSuccess -> updateExchangeRateList(it.data)
            is Error -> toast(it.error)
        }
    }

    private fun updateExchangeRateList(it: List<ExchangeRate>?) {
        currencyViewAdapter.setCurrencyList(it)
    }

    private fun updateBaseRateSpinner(it: CurrencyResponse?) {
        lifecycleScope.launch {
            currencyConverterViewModel.intentAction.send(ConverterIntent.UpdateSpinner(it))
        }
    }

    var changeOfCurrency = ObservableField<ExchangeRate>().apply {
        addOnPropertyChangedCallback(object : Observable.OnPropertyChangedCallback() {
            override fun onPropertyChanged(sender: Observable?, propertyId: Int) {
                this@apply.get()?.let { inputSendToConverter(it.rate) }
            }
        })
    }

    private fun inputSendToConverter(selectedRate: Double?) {
        val inputAmount: Double = getInput()
        lifecycleScope.launch {
            val startConversion = ConverterIntent.Convert(inputAmount, selectedRate)
            currencyConverterViewModel.intentAction.send(startConversion)
        }
    }

    private fun getInput() =
        if (!editTextNumber.text.isNullOrEmpty()) editTextNumber.text.toString().toDouble() else 0.0
}