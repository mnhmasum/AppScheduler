package com.meldcx.appscheduler.ui.currency

import android.util.Log
import androidx.databinding.ObservableField
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
    val intent = Channel<ConverterIntent>()
    val currencyListLiveData = MutableLiveData<CurrencyData>()
    private val _dataState = MutableStateFlow<AppState>(AppState.Idle)
    val dataState: StateFlow<AppState>
        get() = _dataState

    private var text: ObservableField<Rate>? = null
    fun ViewModel() {
        text = ObservableField()
    }

    fun getText(): ObservableField<Rate>? {
        return text
    }

    init {
        handleIntentAction()
    }

    private fun handleIntentAction() {
        viewModelScope.launch {
            intent.consumeAsFlow().collect {
                when (it) {
                    is ConverterIntent.FETCH -> getLatestData()
                    is ConverterIntent.StartConversion -> startConversion(it)
                }
            }
        }
    }

    private fun startConversion(it: ConverterIntent.StartConversion) {
        if (it.amount == 0.0) return
        val convertedRateList = ConverterUtil.convertValueAndGet(
            it.baseRate,
            it.amount,
            currencyListLiveData.value?.list
        )
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
        currencyListLiveData.postValue(dataFromDB)
    }

    fun onTextChangeComplete(text: CharSequence?, position: Int) {
        Log.d("Spinner Changin...", "onTextChange: " + text.toString())
        /*if (text.toString().isNotEmpty() && selectedPosition >= 0) {
            viewModelScope.launch(Dispatchers.IO) {
                intent.send(
                    ConverterIntent.StartConversion(
                        text.toString().toDouble(),
                        currencyListLiveData.value?.list?.get(selectedPosition)?.rate
                    )
                )
            }
        }*/
    }

}