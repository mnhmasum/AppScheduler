package com.meldcx.appscheduler.repository

import com.meldcx.appscheduler.data.AppState
import com.meldcx.appscheduler.data.CurrencyDao
import com.meldcx.appscheduler.data.CurrencyData
import com.meldcx.appscheduler.data.Rate
import com.meldcx.appscheduler.retrofit.apiClient
import com.meldcx.appscheduler.utils.Constant
import retrofit2.Response


class CurrencyListRepository constructor(private val dao: CurrencyDao) :
    CurrencyListRepositoryInterface {
    override suspend fun insert(currencyData: CurrencyData) {
        dao.insert(currencyData)
        dao.insert(currencyData.rateListFromAPI)
    }

    override suspend fun insert(rate: List<Rate>) {
        dao.insert(rate)
    }

    override fun getCurrencyDataFromDB(): CurrencyData {
        val base = dao.currencyBase
        val rates = getCurrencyRate()
        base.setRateList(rates)
        return base
    }

    override fun getCurrencyRate(): List<Rate> {
        return dao.rates
    }

    override suspend fun getCurrencyDataFromApi(): CurrencyData? {
        val dataFromDB = getCurrencyDataFromDB()
        return if (isOfflineAvailable(dataFromDB)) {
            dataFromDB
        } else {
            val result = apiClient().getRepositories(Constant.API_KEY)
            if (result.isSuccessful) {
                result.body()?.let {
                    insert(it)
                }
            }
            result.body()
        }
    }

    private fun isOfflineAvailable(dbResult: CurrencyData) = dbResult.base == "USD"
}