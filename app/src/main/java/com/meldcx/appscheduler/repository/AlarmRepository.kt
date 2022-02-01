package com.meldcx.appscheduler.repository

import androidx.lifecycle.LiveData
import com.meldcx.appscheduler.application.MainApplication
import com.meldcx.appscheduler.application.MainApplication.Companion.databaseWriteExecutor
import com.meldcx.appscheduler.data.Alarm
import com.meldcx.appscheduler.data.AlarmDao

class AlarmRepository() {
    private val alarmDao: AlarmDao = MainApplication.appDatabase.alarmDao()
    val alarmsLiveData: LiveData<List<Alarm>> = alarmDao.alarms

    fun insert(alarm: Alarm?) {
        databaseWriteExecutor.execute { alarmDao.insert(alarm) }
    }

    fun update(alarm: Alarm?) {
        databaseWriteExecutor.execute { alarmDao.update(alarm) }
    }

}