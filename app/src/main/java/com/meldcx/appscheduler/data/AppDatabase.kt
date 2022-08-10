package com.meldcx.appscheduler.data

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

@Database(entities = [Schedule::class, CurrencyResponse::class, ExchangeRate::class], version = 1, exportSchema = true)
@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun alarmDao(): ScheduleDao
    abstract fun currencyDao(): CurrencyDao
}