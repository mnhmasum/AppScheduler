package com.meldcx.appscheduler.repository

import com.meldcx.appscheduler.data.Schedule

interface CreateTaskRepositoryInterface {
    suspend fun insert(schedule: Schedule)
}