package com.meldcx.appscheduler.repo

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.meldcx.appscheduler.data.Schedule
import com.meldcx.appscheduler.repository.ScheduleRepositoryInterface

class FakeArtRepository : ScheduleRepositoryInterface {

    private val scheduleList = mutableListOf<Schedule>()
    private val schedulesLiveData = MutableLiveData<List<Schedule>>(scheduleList)

    override suspend fun insert(schedule: Schedule) {
        scheduleList.add(schedule)
        refreshLiveData()
    }

    override suspend fun update(schedule: Schedule) {
        scheduleList.forEachIndexed { index, item ->
            scheduleList[index] = schedule
        }
        refreshLiveData()
    }

    override suspend fun delete(schedule: Schedule) {
        scheduleList.remove(schedule)
        refreshLiveData()
    }

    override fun getScheduleLiveData(): LiveData<List<Schedule>> {
        return schedulesLiveData
    }

    private fun refreshLiveData() {
        schedulesLiveData.postValue(scheduleList)
    }

}