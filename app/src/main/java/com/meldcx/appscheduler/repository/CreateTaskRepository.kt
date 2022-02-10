package com.meldcx.appscheduler.repository

import com.meldcx.appscheduler.data.Schedule
import com.meldcx.appscheduler.data.ScheduleDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class CreateTaskRepository constructor(private val scheduleDao: ScheduleDao) :
    CreateTaskRepositoryInterface {

    override suspend fun insert(schedule: Schedule) {
        scheduleDao.insert(schedule)
    }

}