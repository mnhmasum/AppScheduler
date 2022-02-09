package com.meldcx.appscheduler.repo

import androidx.lifecycle.MutableLiveData
import com.meldcx.appscheduler.data.Schedule
import com.meldcx.appscheduler.repository.CreateTaskRepositoryInterface

class FakeCreateTaskRepository : CreateTaskRepositoryInterface {
    private val scheduleList = mutableListOf<Schedule>()
    private val schedulesLiveData = MutableLiveData<List<Schedule>>(scheduleList)

    override suspend fun insert(schedule: Schedule) {
        scheduleList.add(schedule)
        refreshLiveData()
    }

    private fun refreshLiveData() {
        schedulesLiveData.postValue(scheduleList)
    }

}