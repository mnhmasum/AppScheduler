package com.meldcx.appscheduler.data

sealed class ConverterIntent {
    object FetchData : ConverterIntent()
    class StartConversion(val amount: Double) : ConverterIntent()
}