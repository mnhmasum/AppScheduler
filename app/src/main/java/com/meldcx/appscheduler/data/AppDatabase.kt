package com.meldcx.appscheduler.data

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [Schedule::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun alarmDao(): ScheduleDao
}