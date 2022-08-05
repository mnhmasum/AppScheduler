package com.meldcx.appscheduler.repository

import androidx.lifecycle.LiveData
import com.meldcx.appscheduler.retrofit.CurrencyData
import retrofit2.Response


interface CurrencyListRepositoryInterface {
    suspend fun getCurrencyRateList(): Response<CurrencyData>
}