package com.meldcx.appscheduler.data

import androidx.room.*

@Dao
interface CurrencyDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(rate: CurrencyData?)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(rate: Rate?)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(rates: List<Rate?>?)

    @Query("DELETE FROM schedule_table")
    fun deleteAll()

    @get:Query("SELECT * FROM currency_rate")
    val rates: List<Rate>

    @get:Query("SELECT * FROM currency_base LIMIT 1")
    val currencyBase: CurrencyData?

    @Update
    fun update(schedule: Schedule?)

    @Delete
    fun delete(schedule: Schedule?)
}