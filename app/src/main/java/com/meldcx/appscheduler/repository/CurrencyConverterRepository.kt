package com.meldcx.appscheduler.repository

import com.meldcx.appscheduler.data.CurrencyDao
import com.meldcx.appscheduler.data.CurrencyResponse
import com.meldcx.appscheduler.data.ExchangeRate
import com.meldcx.appscheduler.retrofit.apiClient
import com.meldcx.appscheduler.utils.Constant


class CurrencyConverterRepository constructor(private val dao: CurrencyDao) : CurrencyConverterRepositoryInterface {
    override suspend fun insert(currencyData: CurrencyResponse) {
        dao.insert(currencyData)
        insert(currencyData.rateListFromAPI)
    }

    override suspend fun insert(exchangeRate: List<ExchangeRate>) {
        dao.insert(exchangeRate)
    }

    override fun getCurrencyDataFromDB(): CurrencyResponse? {
        val base = getCurrencyBaseDB()
        if (base != null) {
            val rates = getCurrencyRateDB()
            base.setExchangeRateList(rates)
        }
        return base
    }

    override fun getCurrencyBaseDB(): CurrencyResponse? {
        return dao.currencyBase
    }

    override fun getCurrencyRateDB(): List<ExchangeRate> {
        return dao.exchangeRates
    }

    override suspend fun getCurrencyData(): CurrencyResponse? {
        val dataFromDB = getCurrencyDataFromDB()
        return when (dataFromDB != null && isOfflineAvailable(dataFromDB)) {
            true -> dataFromDB
            false -> callExchangeRateAPI()
        }
    }

    private suspend fun callExchangeRateAPI(): CurrencyResponse? {
        val result = apiClient().getRepositories(Constant.API_KEY)
        if (result.isSuccessful) {
            result.body()?.let { insert(it) }
        }
        return result.body()
    }

    private fun isOfflineAvailable(dbResult: CurrencyResponse) = dbResult.base == "USD"
}