package com.meldcx.appscheduler.repository

import com.meldcx.appscheduler.data.CurrencyDao
import com.meldcx.appscheduler.data.CurrencyData
import com.meldcx.appscheduler.data.Rate
import com.meldcx.appscheduler.retrofit.apiClient
import com.meldcx.appscheduler.utils.Constant


class CurrencyListRepository constructor(private val dao: CurrencyDao)  : CurrencyListRepositoryInterface {
    override suspend fun insert(currencyData: CurrencyData) {
        dao.insert(currencyData)
        dao.insert(currencyData.rateListFromAPI)
    }

    override suspend fun insert(rate: List<Rate>) {
        dao.insert(rate)
    }

    override fun getCurrencyDataFromDB(): CurrencyData {
        return dao.currencyBase
    }

    override fun getCurrencyRate(): List<Rate> {
        return dao.rates
    }

    override suspend fun getCurrencyDataFromApi() = apiClient().getRepositories(Constant.API_KEY)
}