package com.meldcx.appscheduler.data

sealed class ConverterIntent {
    object FETCH : ConverterIntent()
    class StartConversion(val amount: Double, val baseRate: Double?) : ConverterIntent()
}