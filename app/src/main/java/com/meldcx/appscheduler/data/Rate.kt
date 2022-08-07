package com.meldcx.appscheduler.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "currency_rate")
data class Rate(@PrimaryKey val currencyName: String, var rate: Double?) {
    val readableRate: String
        get() = String.format("%.2f", rate)

}
