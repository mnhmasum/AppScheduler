package com.meldcx.appscheduler.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.meldcx.appscheduler.data.Schedule
import com.meldcx.appscheduler.repository.ScheduleRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainViewModel internal constructor(private val scheduleRepository: ScheduleRepository) :
    ViewModel() {
    var alarmsLiveData: LiveData<List<Schedule>> = scheduleRepository.alarmsLiveData

    fun update(schedule: Schedule) {
        viewModelScope.launch(Dispatchers.IO) {
            scheduleRepository.update(schedule)
        }
    }

    fun delete(schedule: Schedule) {
        viewModelScope.launch(Dispatchers.IO) {
            scheduleRepository.delete(schedule)
        }
    }
}