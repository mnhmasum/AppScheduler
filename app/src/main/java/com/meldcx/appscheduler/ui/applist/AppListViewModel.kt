package com.meldcx.appscheduler.ui.applist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.meldcx.appscheduler.data.AppItem
import com.meldcx.appscheduler.data.AppListIntent
import com.meldcx.appscheduler.data.DataState
import com.meldcx.appscheduler.repository.AppListRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.consumeAsFlow
import kotlinx.coroutines.launch

class AppListViewModel(private val appListRepository: AppListRepository) : ViewModel() {
    val appListIntent = Channel<AppListIntent>(Channel.UNLIMITED)
    private val _dataState = MutableStateFlow<DataState>(DataState.Idle)
    val dataState: StateFlow<DataState>
        get() = _dataState

    init {
        handleIntentAction()
    }

    private fun handleIntentAction() {
        viewModelScope.launch {
            appListIntent.consumeAsFlow().collect {
                when (it) {
                    is AppListIntent.FetchApps -> fetchInstalledApps()
                }
            }
        }
    }

    private fun fetchInstalledApps() {
        viewModelScope.launch(Dispatchers.Default) {
            _dataState.value = DataState.Loading
            _dataState.value = DataState.Apps(appListRepository.getInstalledApps())
        }
    }

    fun selectApp(appItem: AppItem) {
        viewModelScope.launch(Dispatchers.Main) {
            _dataState.value = DataState.Data(appItem)
        }
    }
}