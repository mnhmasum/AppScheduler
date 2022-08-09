package com.meldcx.appscheduler.ui.currency

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.meldcx.appscheduler.data.AppState
import com.meldcx.appscheduler.data.ConverterIntent
import com.meldcx.appscheduler.data.CurrencyData
import com.meldcx.appscheduler.data.Rate
import com.meldcx.appscheduler.repository.CurrencyListRepositoryInterface
import com.meldcx.appscheduler.utils.ConverterUtil
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.consumeAsFlow
import kotlinx.coroutines.launch

class CurrencyViewModel internal constructor(private val currencyRepoInterface: CurrencyListRepositoryInterface) :
    ViewModel() {
    val intentAction = Channel<ConverterIntent>()
    val currencyLiveData = MutableLiveData<CurrencyData>()
    private var selectedRate = MutableLiveData<Double>(0.0)
    private val _dataState = MutableStateFlow<AppState>(AppState.Idle)
    val dataState: StateFlow<AppState>
        get() = _dataState

    init {
        handleIntentAction()
    }

    private fun handleIntentAction() {
        viewModelScope.launch {
            intentAction.consumeAsFlow().collect {
                when (it) {
                    is ConverterIntent.FETCH -> fetchCurrencyData()
                    is ConverterIntent.StartConversion -> startConversion(it)
                }
            }
        }
    }

    private fun fetchCurrencyData() {
        viewModelScope.launch(Dispatchers.IO) {
            _dataState.value = AppState.Loading
            _dataState.value = AppState.Success(fetchDataFromRepository())
        }
    }

    private suspend fun fetchDataFromRepository(): CurrencyData? {
        val result = currencyRepoInterface.getCurrencyData()
        result?.let { updateSpinnerData(it) }
        return result
    }

    private fun startConversion(it: ConverterIntent.StartConversion) {
        viewModelScope.launch(Dispatchers.IO) {
            _dataState.value = AppState.Loading
            _dataState.value = AppState.CompletedConversion(getConvertedCurrencyList(it))
        }
    }

    private fun getConvertedCurrencyList(it: ConverterIntent.StartConversion): List<Rate> {
        it.baseRate?.let { selectedRate.postValue(it) }
        val convertedRateList = ConverterUtil.convertValueAndGet(it.baseRate, it.amount, currencyLiveData.value?.rateList)
        if (it.amount == 0.0) (convertedRateList as ArrayList<Rate>).clear()
        return convertedRateList
    }

    private fun updateSpinnerData(dataFromDB: CurrencyData) {
        currencyLiveData.postValue(dataFromDB)
    }

    fun onTextChangeComplete(text: CharSequence?) {
        val input = if (text.toString().isNotEmpty()) text.toString().toDouble() else 0.0
        viewModelScope.launch(Dispatchers.IO) {
            intentAction.send(ConverterIntent.StartConversion(input, selectedRate.value))
        }
    }

}