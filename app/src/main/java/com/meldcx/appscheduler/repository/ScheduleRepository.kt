package com.meldcx.appscheduler.repository

import androidx.lifecycle.LiveData
import com.meldcx.appscheduler.data.Schedule
import com.meldcx.appscheduler.data.ScheduleDao

class ScheduleRepository constructor(private val scheduleDao: ScheduleDao) : ScheduleRepositoryInterface {
    val alarmsLiveData: LiveData<List<Schedule>> = scheduleDao.schedules

    override suspend fun insert(schedule: Schedule) {
        scheduleDao.insert(schedule)
    }

    override suspend fun update(schedule: Schedule) {
        scheduleDao.update(schedule)
    }

    override suspend fun delete(schedule: Schedule) {
        scheduleDao.delete(schedule)
    }

    override fun getScheduleLiveData(): LiveData<List<Schedule>> {
        return alarmsLiveData
    }

}