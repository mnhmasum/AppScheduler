package com.meldcx.appscheduler.ui.currency

import android.util.Log
import androidx.databinding.Observable
import androidx.databinding.ObservableField
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.meldcx.appscheduler.data.*
import com.meldcx.appscheduler.repository.CurrencyListRepositoryInterface
import com.meldcx.appscheduler.utils.Constant
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.consumeAsFlow
import kotlinx.coroutines.launch

class CurrencyViewModel internal constructor(private val currencyRepoInterface: CurrencyListRepositoryInterface) : ViewModel() {
    val mutableLiveData = MutableLiveData<CurrencyData>()
    //var scheduleLiveData: LiveData<CurrencyData> = currencyRepoInterface.getCurrencyDataFromDB()
    val intent = Channel<AppListIntent>()
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
        val dbResult = currencyRepoInterface.getCurrencyDataFromDB()
        Log.d("ISDB==>", "getLatestCurrencyRates: " + dbResult)
        if(dbResult?.base == "USD") {
            //dbResult.rateList.
            mutableLiveData.postValue(dbResult)
            return AppState.Success(dbResult)
        } else {
            val result = currencyRepoInterface.getCurrencyDataFromApi()
            if (result.isSuccessful) {
                val dbResult = currencyRepoInterface.getCurrencyDataFromDB()
                Log.d("ISDB==>", "getLatestCurrencyRates: " + dbResult?.base)
                result.body()?.let {
                    currencyRepoInterface.insert(it)
                    currencyRepoInterface.insert(it.rateList)
                    mutableLiveData.postValue(result.body())
                    return AppState.Success(result.body())
                }
            }
        }

        return AppState.Error(Constant.ERROR_MSG)
    }

    val rateOfCurrency = ObservableField<Rate>().apply {
        addOnPropertyChangedCallback(object : Observable.OnPropertyChangedCallback() {
            override fun onPropertyChanged(sender: Observable?, propertyId: Int) {
                this@apply.get()?.let { Log.d("value", it.currencyName) } //selected value
            }
        })
    }

    /* fun update(schedule: Schedule) = viewModelScope.launch(Dispatchers.IO) {
         currencyListRepositoryInterface.update(schedule)
     }d

     fun insert(schedule: Schedule) = viewModelScope.launch(Dispatchers.IO) {
         currencyListRepositoryInterface.insert(schedule)
     }

     fun delete(schedule: Schedule) = viewModelScope.launch(Dispatchers.IO) {
         currencyListRepositoryInterface.delete(schedule)
     }*/


}