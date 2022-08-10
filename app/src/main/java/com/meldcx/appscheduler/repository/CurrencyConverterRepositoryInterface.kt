package com.meldcx.appscheduler.repository

import com.meldcx.appscheduler.data.CurrencyResponse
import com.meldcx.appscheduler.data.ExchangeRate


interface CurrencyConverterRepositoryInterface {
    suspend fun insert(currencyData: CurrencyResponse)
    suspend fun insert(exchangeRate: List<ExchangeRate>)
    suspend fun getCurrencyData(): CurrencyResponse?
    fun getCurrencyDataFromDB(): CurrencyResponse?
    fun getCurrencyBaseDB(): CurrencyResponse?
    fun getCurrencyRateDB(): List<ExchangeRate>
}