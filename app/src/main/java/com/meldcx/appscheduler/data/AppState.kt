package com.meldcx.appscheduler.data

sealed class AppState {
    object Idle : AppState()
    object Loading : AppState()
    data class ShowApps(val data: List<AppItem>) : AppState()
    data class App(val app: AppItem) : AppState()
    data class Error(val error: String) : AppState()
}