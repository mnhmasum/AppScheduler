package com.meldcx.appscheduler.ui.applist

import android.content.ContentValues.TAG
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.meldcx.appscheduler.data.*
import com.meldcx.appscheduler.repository.AppListRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.consumeAsFlow
import kotlinx.coroutines.launch
import java.lang.Exception

class AppListViewModel(private val appListRepository: AppListRepository) : ViewModel() {
    val appListIntent = Channel<AppListIntent>(Channel.UNLIMITED)
    private val _dataState = MutableStateFlow<AppState>(AppState.Idle)
    private val _dataState1 = MutableStateFlow<DataState<List<AppItem>>>(DataState.Loading(null))
    val dataState: StateFlow<AppState>
        get() = _dataState

    val dataState1: StateFlow<DataState<List<AppItem>>>
        get() = _dataState1

    init {
        handleIntentAction()
    }

    private fun handleIntentAction() {
        viewModelScope.launch {
            appListIntent.consumeAsFlow().collect {
                when (it) {
                    is AppListIntent.FetchApps -> testFetch()
                }
            }
        }
    }

    private fun fetchInstalledApps() {
        viewModelScope.launch(Dispatchers.Default) {
            _dataState.value = AppState.Loading
            _dataState.value = AppState.ShowApps(appListRepository.getInstalledApps())
        }
    }

    private suspend fun testFetch() {
        Log.d(TAG, "testFetch: ")
        viewModelScope.launch(Dispatchers.Default) {
            //_dataState1.value = DataState.Success(appListRepository.getAppItemTest())
            _dataState1.value = DataState.Error(null, Exception(""))
        }
    }

    fun selectApp(appItem: AppItem) {
        viewModelScope.launch(Dispatchers.Main) {
            _dataState.value = AppState.App(appItem)
        }
    }
}