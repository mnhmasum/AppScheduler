package com.meldcx.appscheduler.data

sealed class AppState {
    object Idle : AppState()
    object Loading : AppState()
    data class Apps(val data: List<AppItem>) : AppState()
    data class App(val app: AppItem) : AppState()
    data class Success(val data: CurrencyData?) : AppState()
    data class CompletedConversion(val data: List<Rate>) : AppState()
    data class Error(val error: String) : AppState()
}