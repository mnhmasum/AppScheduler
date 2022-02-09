package com.meldcx.appscheduler.repository

import androidx.lifecycle.LiveData
import com.meldcx.appscheduler.data.Schedule
import com.meldcx.appscheduler.data.ScheduleDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class ScheduleRepository constructor(private val alarmDao: ScheduleDao) :
    ScheduleRepositoryInterface {
    val alarmsLiveData: LiveData<List<Schedule>> = alarmDao.alarms

    override suspend fun insert(schedule: Schedule) {
        alarmDao.insert(schedule)
    }

    override suspend fun update(schedule: Schedule) {
        alarmDao.update(schedule)
    }

    override suspend fun delete(schedule: Schedule) {
        alarmDao.delete(schedule)
    }

    override fun getScheduleLiveData(): LiveData<List<Schedule>> {
        return alarmsLiveData
    }

}