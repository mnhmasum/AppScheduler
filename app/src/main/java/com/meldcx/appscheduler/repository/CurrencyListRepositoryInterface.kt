package com.meldcx.appscheduler.repository

import androidx.lifecycle.LiveData
import com.meldcx.appscheduler.data.CurrencyData
import com.meldcx.appscheduler.data.Rate
import retrofit2.Response


interface CurrencyListRepositoryInterface {
    suspend fun insert(currencyData: CurrencyData)
    suspend fun insert(rate: List<Rate>)
    suspend fun getCurrencyData(): CurrencyData?
    fun getCurrencyLatestData(): CurrencyData?
    fun getCurrencyRate(): List<Rate>
}