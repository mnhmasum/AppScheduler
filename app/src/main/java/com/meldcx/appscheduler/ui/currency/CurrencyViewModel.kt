package com.meldcx.appscheduler.ui.currency

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.meldcx.appscheduler.data.AppState
import com.meldcx.appscheduler.data.ConverterIntent
import com.meldcx.appscheduler.data.CurrencyData
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
    val intent = Channel<ConverterIntent>()
    val currencyDataLive = MutableLiveData<CurrencyData>()
    private val _dataState = MutableStateFlow<AppState>(AppState.Idle)
    val dataState: StateFlow<AppState>
        get() = _dataState

    init {
        handleIntentAction()
    }

    private fun handleIntentAction() {
        viewModelScope.launch {
            intent.consumeAsFlow().collect {
                when (it) {
                    is ConverterIntent.FetchData -> getLatestData()
                    is ConverterIntent.StartConversion -> startConversion(it)
                }
            }
        }
    }

    private fun startConversion(it: ConverterIntent.StartConversion) {
        if (it.amount == 0.0) return
        val convertedRateList = ConverterUtil.convertValueAndGet(it.amount, currencyDataLive.value?.list)
        viewModelScope.launch(Dispatchers.IO) {
            _dataState.value = AppState.Loading
            _dataState.value = AppState.CompletedConversion(convertedRateList)
        }
    }

    private fun getLatestData() {
        viewModelScope.launch(Dispatchers.IO) {
            _dataState.value = AppState.Loading
            _dataState.value = AppState.Success(getCurrencyRateFromAPI())
        }
    }

    private suspend fun getCurrencyRateFromAPI(): CurrencyData? {
        val result = currencyRepoInterface.getCurrencyData()
        result?.let { updateSpinnerData(it) }
        return result
    }

    private fun updateSpinnerData(dataFromDB: CurrencyData) {
        currencyDataLive.postValue(dataFromDB)
    }

    fun onTextChange(text: CharSequence?) {
        if (text.toString().isNotEmpty()) {
            viewModelScope.launch(Dispatchers.IO) {
                intent.send(ConverterIntent.StartConversion(text.toString().toDouble()))
            }
        }
    }

}