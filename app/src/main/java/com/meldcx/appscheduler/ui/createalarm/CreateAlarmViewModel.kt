package com.meldcx.appscheduler.ui.createalarm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.meldcx.appscheduler.data.Alarm
import com.meldcx.appscheduler.repository.AlarmRepository

class CreateAlarmViewModel internal constructor(private val alarmRepository: AlarmRepository) : ViewModelProvider.Factory {
    fun insert(alarm: Alarm?) {
        alarmRepository.insert(alarm)
    }

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return CreateAlarmViewModel(alarmRepository) as T
    }
}