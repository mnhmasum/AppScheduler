package com.meldcx.appscheduler.repository

import com.meldcx.appscheduler.data.CurrencyDao
import com.meldcx.appscheduler.data.CurrencyData
import com.meldcx.appscheduler.data.Rate
import com.meldcx.appscheduler.retrofit.apiClient
import com.meldcx.appscheduler.utils.Constant


class CurrencyListRepository constructor(private val dao: CurrencyDao) :
    CurrencyListRepositoryInterface {
    override suspend fun insert(currencyData: CurrencyData) {
        dao.insert(currencyData)
        insert(currencyData.rateListFromAPI)
    }

    override suspend fun insert(rate: List<Rate>) {
        dao.insert(rate)
    }

    override fun getCurrencyLatestData(): CurrencyData? {
        val base = dao.currencyBase
        if (base != null) {
            val rates = getCurrencyRate()
            base.setRateList(rates)
        }
        return base
    }

    override fun getCurrencyRate(): List<Rate> {
        return dao.rates
    }

    override suspend fun getCurrencyData(): CurrencyData? {
        val dataFromDB = getCurrencyLatestData()
        return if (dataFromDB != null && isOfflineAvailable(dataFromDB)) {
            dataFromDB
        } else {
            val result = apiClient().getRepositories(Constant.API_KEY)
            if (result.isSuccessful) {
                result.body()?.let { insert(it) }
            }
            result.body()
        }
    }

    private fun isOfflineAvailable(dbResult: CurrencyData) = dbResult.base == "USD"
}