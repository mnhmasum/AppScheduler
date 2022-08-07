package com.meldcx.appscheduler.ui.currency

import android.util.Log
import androidx.databinding.Observable
import androidx.databinding.ObservableField
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.meldcx.appscheduler.data.AppListIntent
import com.meldcx.appscheduler.data.AppState
import com.meldcx.appscheduler.data.CurrencyData
import com.meldcx.appscheduler.data.Rate
import com.meldcx.appscheduler.repository.CurrencyListRepositoryInterface
import com.meldcx.appscheduler.utils.Constant
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
    val intent = Channel<AppListIntent>()
    val mutableLiveData = MutableLiveData<CurrencyData>()
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
                    is AppListIntent.FetchApps -> getLatestData()
                }
            }
        }
    }

    private fun getLatestData() {
        viewModelScope.launch(Dispatchers.IO) {
            _dataState.value = AppState.Loading
            _dataState.value = AppState.Success(getCurrencyRateFromAPI())
        }
    }

    private suspend fun getCurrencyRateFromAPI(): CurrencyData? {
        val result = currencyRepoInterface.getCurrencyDataFromApi()
        result?.let { updateSpinnerData(it) }
        return result
    }

    private fun updateSpinnerData(dataFromDB: CurrencyData) {
        mutableLiveData.postValue(dataFromDB)
    }

    val rateOfCurrency = ObservableField<Rate>().apply {
        addOnPropertyChangedCallback(object : Observable.OnPropertyChangedCallback() {
            override fun onPropertyChanged(sender: Observable?, propertyId: Int) {
                _dataState.value = AppState.Loading
                this@apply.get()?.let { Log.d("value", it.currencyName) } //selected value
                //ConverterUtil.convertValueAndGet()
            }
        })
    }

}