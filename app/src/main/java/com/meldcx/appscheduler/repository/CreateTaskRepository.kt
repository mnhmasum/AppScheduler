package com.meldcx.appscheduler.repository

import com.meldcx.appscheduler.data.Schedule
import com.meldcx.appscheduler.data.ScheduleDao

class CreateTaskRepository constructor(private val scheduleDao: ScheduleDao) :
    CreateTaskRepositoryImpl {

    override suspend fun insert(schedule: Schedule) {
        scheduleDao.insert(schedule)
    }

}