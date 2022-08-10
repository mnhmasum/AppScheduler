package com.meldcx.appscheduler.data

sealed class ConverterIntent {
    object Fetch : ConverterIntent()
    class Convert(val amount: Double, val baseRate: Double?) : ConverterIntent()
    class UpdateSpinner(val currencyResponse: CurrencyResponse?) : ConverterIntent()
}