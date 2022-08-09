package com.meldcx.appscheduler.repository

import com.meldcx.appscheduler.data.CurrencyDao
import com.meldcx.appscheduler.data.CurrencyData
import com.meldcx.appscheduler.data.Rate
import com.meldcx.appscheduler.retrofit.apiClient
import com.meldcx.appscheduler.utils.Constant


class CurrencyDataRepository constructor(private val dao: CurrencyDao) : CurrencyDataRepositoryInterface {
    override suspend fun insert(currencyData: CurrencyData) {
        dao.insert(currencyData)
        insert(currencyData.rateListFromAPI)
    }

    override suspend fun insert(rate: List<Rate>) {
        dao.insert(rate)
    }

    override fun getCurrencyDataFromDB(): CurrencyData? {
        val base = getCurrencyBaseDB()
        if (base != null) {
            val rates = getCurrencyRateDB()
            base.setRateList(rates)
        }
        return base
    }

    override fun getCurrencyBaseDB(): CurrencyData? {
        return dao.currencyBase
    }

    override fun getCurrencyRateDB(): List<Rate> {
        return dao.rates
    }

    override suspend fun getCurrencyData(): CurrencyData? {
        val dataFromDB = getCurrencyDataFromDB()
        return when (dataFromDB != null && isOfflineAvailable(dataFromDB)) {
            true -> dataFromDB
            false -> callCurrencyAPI()
        }
    }

    private suspend fun callCurrencyAPI(): CurrencyData? {
        val result = apiClient().getRepositories(Constant.API_KEY)
        if (result.isSuccessful) {
            result.body()?.let { insert(it) }
        }
        return result.body()
    }

    private fun isOfflineAvailable(dbResult: CurrencyData) = dbResult.base == "USD"
}