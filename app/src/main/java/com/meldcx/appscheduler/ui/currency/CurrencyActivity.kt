package com.meldcx.appscheduler.ui.currency

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.core.view.isVisible
import androidx.databinding.Observable
import androidx.databinding.ObservableField
import androidx.lifecycle.lifecycleScope
import com.meldcx.appscheduler.R
import com.meldcx.appscheduler.data.AppState
import com.meldcx.appscheduler.data.ConverterIntent
import com.meldcx.appscheduler.data.Rate
import com.meldcx.appscheduler.databinding.ActivityCurrencyConvertBinding
import com.meldcx.appscheduler.dependencyinjection.MainActivityComponent
import com.meldcx.appscheduler.ui.base.BaseActivity
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
                currencyViewModel.intent.send(ConverterIntent.FETCH)
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

    val changeOfCurrency = ObservableField<Rate>().apply {
        addOnPropertyChangedCallback(object : Observable.OnPropertyChangedCallback() {
            override fun onPropertyChanged(sender: Observable?, propertyId: Int) {
                this@apply.get()?.let { inputSendToConverter(it.rate) }
            }
        })
    }

    private fun inputSendToConverter(selectedRate:Double?) {
        val inputAmount: Double = getInput()
        lifecycleScope.launch {
            val startConversion = ConverterIntent.StartConversion(inputAmount, selectedRate)
            currencyViewModel.intent.send(startConversion)
        }
    }

    private fun getInput() =
        if (!editTextNumber.text.isNullOrEmpty()) editTextNumber.text.toString().toDouble() else 0.0
}