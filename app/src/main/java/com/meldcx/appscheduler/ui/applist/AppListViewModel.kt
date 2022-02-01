package com.meldcx.appscheduler.ui.applist

import android.content.pm.PackageManager
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.meldcx.appscheduler.data.AppItem
import com.meldcx.appscheduler.repository.AppListRepository
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.consumeAsFlow
import kotlinx.coroutines.launch

class AppListViewModel( pm: PackageManager) : ViewModel() {
    val userIntent = Channel<MainIntent>(Channel.UNLIMITED)
    private val _state = MutableStateFlow<MainState>(MainState.Idle)
    val state: StateFlow<MainState>
        get() = _state
    var mutableLiveData: MutableLiveData<List<AppItem>>? = null

    init {
        //handleIntent()
        val appRepository = AppListRepository(pm)
        mutableLiveData = appRepository.getApps()
    }

    private fun handleIntent() {
        viewModelScope.launch {
            userIntent.consumeAsFlow().collect {
                when (it) {
                    is MainIntent.FetchUser -> fetchUser()
                }
            }
        }
    }

    private fun fetchUser() {
        viewModelScope.launch {
            _state.value = MainState.Loading
            _state.value = try {
                MainState.Apps(ApiHelperImpl().getApps())
            } catch (e: Exception) {
                MainState.Error(e.localizedMessage)
            }
        }
    }
}