package com.meldcx.appscheduler.data

import com.meldcx.appscheduler.retrofit.Rates

sealed class AppState {
    object Idle : AppState()
    object Loading : AppState()
    data class Apps(val data: List<AppItem>) : AppState()
    data class App(val app: AppItem) : AppState()
    data class Success(val list : List<Rates>) : AppState()
    data class Error(val error: String) : AppState()
}