package com.meldcx.appscheduler.repository

import com.meldcx.appscheduler.data.Schedule

interface ScheduleRepositoryInterface {
    suspend fun insert(schedule: Schedule)
    suspend fun update(schedule: Schedule)
    suspend fun delete(schedule: Schedule)
}