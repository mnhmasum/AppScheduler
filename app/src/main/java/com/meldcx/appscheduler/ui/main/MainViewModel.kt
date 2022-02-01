package com.meldcx.appscheduler.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.meldcx.appscheduler.data.Alarm
import com.meldcx.appscheduler.repository.AlarmRepository

class MainViewModel internal constructor(private val alarmRepository: AlarmRepository) :
    ViewModelProvider.Factory {
    var alarmsLiveData: LiveData<List<Alarm>> = alarmRepository.alarmsLiveData

    fun update(alarm: Alarm?) {
        alarmRepository.update(alarm)
    }

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return MainViewModel(alarmRepository) as T
    }
}