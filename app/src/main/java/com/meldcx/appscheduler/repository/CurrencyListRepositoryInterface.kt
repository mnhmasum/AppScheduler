package com.meldcx.appscheduler.repository

import androidx.lifecycle.LiveData
import com.meldcx.appscheduler.data.CurrencyData
import com.meldcx.appscheduler.data.Rate
import retrofit2.Response


interface CurrencyListRepositoryInterface {
    suspend fun insert(currencyData: CurrencyData)
    suspend fun insert(rate: List<Rate>)
    suspend fun getCurrencyDataFromApi(): CurrencyData?
    fun getCurrencyDataFromDB(): CurrencyData
    fun getCurrencyRate(): List<Rate>
}