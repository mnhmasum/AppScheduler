package com.meldcx.appscheduler.data

import com.meldcx.appscheduler.retrofit.CurrencyData
import retrofit2.Response

sealed class AppState {
    object Idle : AppState()
    object Loading : AppState()
    data class Apps(val data: List<AppItem>) : AppState()
    data class App(val app: AppItem) : AppState()
    data class Success(val list: Response<CurrencyData>?) : AppState()
    data class Error(val error: String) : AppState()
}