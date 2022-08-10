package com.meldcx.appscheduler.repo

import androidx.lifecycle.MutableLiveData
import com.meldcx.appscheduler.data.CurrencyResponse
import com.meldcx.appscheduler.data.ExchangeRate
import com.meldcx.appscheduler.repository.CurrencyConverterRepositoryInterface

class FakeCurrencyConverterRepository : CurrencyConverterRepositoryInterface {

    private var currencyData = CurrencyResponse()
    private val currencyLiveData = MutableLiveData<CurrencyResponse>(currencyData)

    override suspend fun insert(currencyData: CurrencyResponse) {
        this.currencyData = currencyData
        this.currencyLiveData.postValue(currencyData)
    }

    override suspend fun insert(exchangeRate: List<ExchangeRate>) {
        this.currencyData.exchangeRateList.addAll(exchangeRate)
        this.currencyLiveData.postValue(currencyData)
    }

    override suspend fun getCurrencyData(): CurrencyResponse? {
        val dataFromDB = getCurrencyDataFromDB()
        return when (dataFromDB != null && isOfflineAvailable(dataFromDB)) {
            true -> dataFromDB
            false -> callCurrencyAPI()
        }
    }

    override fun getCurrencyDataFromDB(): CurrencyResponse? {
        val baseResult = getCurrencyBaseDB()
        val rateResult = getCurrencyRateDB()
        baseResult?.setExchangeRateList(rateResult)
        return baseResult
    }

    override fun getCurrencyBaseDB(): CurrencyResponse? {
        return currencyDummyData()
    }

    override fun getCurrencyRateDB(): List<ExchangeRate> {
        val rate = ExchangeRate("BDT", 94.62)
        val exchangeRates: ArrayList<ExchangeRate> = ArrayList()
        exchangeRates.add(rate)
        return exchangeRates
    }

    private fun callCurrencyAPI(): CurrencyResponse? {
        return getCurrencyDataFromDB()
    }

    private fun currencyDummyData(): CurrencyResponse {
        val currencyData = CurrencyResponse()
        currencyData.id = 123
        currencyData.base = "USD"
        currencyData.license = "randomcharacters"
        currencyData.disclaimer = "some text"
        currencyData.timestamp = 1000000
        return currencyData
    }

    private fun isOfflineAvailable(dbResult: CurrencyResponse) = dbResult.base == "USD"

}