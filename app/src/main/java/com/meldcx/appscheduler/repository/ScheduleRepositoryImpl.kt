package com.meldcx.appscheduler.repository

import androidx.lifecycle.LiveData
import com.meldcx.appscheduler.data.Schedule

interface ScheduleRepositoryImpl {
    suspend fun insert(schedule: Schedule)
    suspend fun update(schedule: Schedule)
    suspend fun delete(schedule: Schedule)
    fun getScheduleLiveData(): LiveData<List<Schedule>>
}