package com.meldcx.appscheduler.repository

import com.meldcx.appscheduler.data.Schedule

interface CreateTaskRepositoryImpl {
    suspend fun insert(schedule: Schedule)
}