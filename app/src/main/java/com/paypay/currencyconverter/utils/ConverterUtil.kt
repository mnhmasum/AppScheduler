package com.paypay.currencyconverter.utils

import android.content.Context
import com.paypay.currencyconverter.data.ExchangeRate

class ConverterUtil(var context: Context) {
    fun baseToDollar(base: Double, dollarRate: Double): Double {
        return dollarRate / base
    }

    companion object {
        fun convertValueAndGet(selectedBase: Double?, inputValue: Double?, exchangeRateList: List<ExchangeRate>?): List<ExchangeRate> {
            val updatedExchangeRateList: ArrayList<ExchangeRate> = ArrayList()
            val usDollar = getDollarRate(exchangeRateList)
            if (usDollar != null && exchangeRateList != null && inputValue != null) {
                for (rate in exchangeRateList) {
                    val rateInSelectedBase = convertToBaseRate(selectedBase ?: 0.0, rate.rate ?: 0.0, usDollar.rate)
                    val result = inputValue * rateInSelectedBase
                    val newRate = ExchangeRate(rate.currencyName, result)
                    updatedExchangeRateList.add(newRate)
                }
            }

            return updatedExchangeRateList
        }

        private fun getDollarRate(exchangeRateList: List<ExchangeRate>?): ExchangeRate? {
            return exchangeRateList?.find { it.currencyName == "USD" }
        }

        fun convertToBaseRate(baseRate: Double?, selectedCurrency: Double?, dollarRate: Double?): Double {
            return if (dollarRate != null && baseRate != null && selectedCurrency != null) {
                val selectedCurrencyToDollar = dollarRate / selectedCurrency
                val baseToDollar = dollarRate / baseRate
                baseToDollar / selectedCurrencyToDollar
            } else {
                0.0
            }
        }
    }
}