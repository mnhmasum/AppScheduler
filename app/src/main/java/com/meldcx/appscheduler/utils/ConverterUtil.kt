package com.meldcx.appscheduler.utils

import android.content.Context
import android.net.ConnectivityManager
import com.meldcx.appscheduler.data.Rate

class ConverterUtil(var context: Context) {
    fun baseToDollar(base: Double, dollarRate: Double): Double {
        return dollarRate / base
    }

    companion object {
        fun convertValueAndGet(
            selectedBase: Double?,
            inputValue: Double?,
            rateList: List<Rate>?
        ): List<Rate> {
            val updatedRateList: MutableList<Rate> = ArrayDeque()
            val usDollar = getDollarRate(rateList)
            if (usDollar != null && rateList != null && inputValue != null) {
                for (rate in rateList) {
                    val rateInSelectedBase = convertToBaseRate(selectedBase ?: 0.0, rate.rate ?: 0.0, usDollar.rate)
                    val result = inputValue * rateInSelectedBase
                    val newRate = Rate(rate.currencyName, result)
                    updatedRateList.add(newRate)
                }
            }

            return updatedRateList
        }

        private fun getDollarRate(rateList: List<Rate>?): Rate? {
            return rateList?.find { it.currencyName == "USD" }
        }

        fun convertToBaseRate(
            baseRate: Double?,
            selectedCurrency: Double?,
            dollarRate: Double?
        ): Double {
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