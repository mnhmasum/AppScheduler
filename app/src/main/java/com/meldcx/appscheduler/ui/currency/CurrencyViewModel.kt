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
            _dataState.value = getLatestCurrencyRates()
        }
    }

    private suspend fun getLatestCurrencyRates(): AppState {
        val dataFromDB = currencyRepoInterface.getCurrencyDataFromDB()
        return if (isOfflineAvailable(dataFromDB)) {
            processDatabase(dataFromDB)
        } else {
            getCurrencyRateFromAPI()
        }
    }

    private fun processDatabase(dataFromDB: CurrencyData): AppState.Success {
        dataFromDB.setRateList(getCurrencyRateFromDB())
        updateSpinnerData(dataFromDB)
        return AppState.Success(dataFromDB)
    }

    private fun updateSpinnerData(dataFromDB: CurrencyData) {
        mutableLiveData.postValue(dataFromDB)
    }

    private fun getCurrencyRateFromDB(): List<Rate> {
        return currencyRepoInterface.getCurrencyRate()
    }

    private suspend fun getCurrencyRateFromAPI(): AppState {
        val result = currencyRepoInterface.getCurrencyDataFromApi()
        if (result.isSuccessful) {
            result.body()?.let {
                currencyRepoInterface.insert(it)
                updateSpinnerData(it)
                return AppState.Success(result.body())
            }
        }

        return AppState.Error(Constant.ERROR_MSG)
    }

    val rateOfCurrency = ObservableField<Rate>().apply {
        addOnPropertyChangedCallback(object : Observable.OnPropertyChangedCallback() {
            override fun onPropertyChanged(sender: Observable?, propertyId: Int) {
                _dataState.value = AppState.Loading

                this@apply.get()?.let { Log.d("value", it.currencyName) } //selected value
            }
        })
    }

    private fun isOfflineAvailable(dbResult: CurrencyData) = dbResult.base == "USD"


}