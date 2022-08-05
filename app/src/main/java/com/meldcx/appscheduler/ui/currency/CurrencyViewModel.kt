package com.meldcx.appscheduler.ui.currency

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.meldcx.appscheduler.data.AppListIntent
import com.meldcx.appscheduler.data.AppState
import com.meldcx.appscheduler.repository.CurrencyListRepositoryInterface
import com.meldcx.appscheduler.retrofit.CurrencyData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.consumeAsFlow
import kotlinx.coroutines.launch

class CurrencyViewModel internal constructor(private val currencyListRepositoryInterface: CurrencyListRepositoryInterface) :
    ViewModel() {
    var currencyLiveData: LiveData<CurrencyData>? = MutableLiveData<CurrencyData>()
    val appListIntent = Channel<AppListIntent>()
    private val _dataState = MutableStateFlow<AppState>(AppState.Idle)
    val dataState: StateFlow<AppState>
        get() = _dataState

    init {
        handleIntentAction()
    }

    private fun handleIntentAction() {
        viewModelScope.launch {
            appListIntent.consumeAsFlow().collect {
                when (it) {
                    is AppListIntent.FetchApps -> getLatestData()
                }
            }
        }
    }

    private fun getLatestData() {
        viewModelScope.launch(Dispatchers.IO) {
            val result = currencyListRepositoryInterface.getCurrencyRateList()
            _dataState.value = AppState.Loading
            _dataState.value = if (result.isSuccessful) AppState.Success(result) else AppState.Error("Data not found")
        }
    }

    /* fun update(schedule: Schedule) = viewModelScope.launch(Dispatchers.IO) {
         currencyListRepositoryInterface.update(schedule)
     }

     fun insert(schedule: Schedule) = viewModelScope.launch(Dispatchers.IO) {
         currencyListRepositoryInterface.insert(schedule)
     }

     fun delete(schedule: Schedule) = viewModelScope.launch(Dispatchers.IO) {
         currencyListRepositoryInterface.delete(schedule)
     }*/


}