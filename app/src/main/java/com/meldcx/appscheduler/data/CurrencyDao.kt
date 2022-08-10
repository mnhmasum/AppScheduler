package com.meldcx.appscheduler.data

import androidx.room.*

@Dao
interface CurrencyDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(rate: CurrencyResponse?)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(exchangeRate: ExchangeRate?)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(exchangeRates: List<ExchangeRate?>?)

    @Query("DELETE FROM schedule_table")
    fun deleteAll()

    @get:Query("SELECT * FROM currency_rate")
    val exchangeRates: List<ExchangeRate>

    @get:Query("SELECT * FROM currency_base LIMIT 1")
    val currencyBase: CurrencyResponse?

    @Update
    fun update(schedule: Schedule?)

    @Delete
    fun delete(schedule: Schedule?)
}