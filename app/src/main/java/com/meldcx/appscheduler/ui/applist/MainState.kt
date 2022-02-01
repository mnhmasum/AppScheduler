package com.meldcx.appscheduler.ui.applist

import com.meldcx.appscheduler.data.AppItem
import kotlinx.coroutines.Deferred

sealed class MainState {
    object Idle : MainState()
    object Loading : MainState()
    data class Apps(val apps: List<AppItem>) : MainState()
    data class Error(val error: String?) : MainState()
}