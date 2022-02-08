package com.meldcx.appscheduler.repository

import androidx.lifecycle.LiveData
import com.meldcx.appscheduler.application.MainApplication.Companion.databaseWriteExecutor
import com.meldcx.appscheduler.data.Schedule
import com.meldcx.appscheduler.data.ScheduleDao

class ScheduleRepository constructor(private val alarmDao: ScheduleDao) :
    ScheduleRepositoryInterface {
    //private val alarmDao: AlarmDao = MainApplication.appDatabase.alarmDao()
    val alarmsLiveData: LiveData<List<Schedule>> = alarmDao.alarms

    /* override fun insert(schedule: Schedule?) {
         databaseWriteExecutor.execute { alarmDao.insert(schedule) }
     }

     fun update(schedule: Schedule?) {
         databaseWriteExecutor.execute { alarmDao.update(schedule) }
     }

     fun delete(schedule: Schedule?) {
         databaseWriteExecutor.execute { alarmDao.delete(schedule) }
     }*/

    override suspend fun insert(schedule: Schedule) {
        //databaseWriteExecutor.execute {  }
        alarmDao.insert(schedule)
    }

    override suspend fun update(schedule: Schedule) {
        //databaseWriteExecutor.execute {  }
        alarmDao.update(schedule)
    }

    override suspend fun delete(schedule: Schedule) {
        //databaseWriteExecutor.execute {  }
        alarmDao.delete(schedule)
    }

}