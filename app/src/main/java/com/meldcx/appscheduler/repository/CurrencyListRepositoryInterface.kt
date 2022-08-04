package com.meldcx.appscheduler.repository

import com.meldcx.appscheduler.data.AppItem
import com.meldcx.appscheduler.retrofit.Rates


interface CurrencyListRepositoryInterface {
    suspend fun getCurrencyRateList(): List<Rates>
}