package com.meldcx.appscheduler.ui.main

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.meldcx.appscheduler.data.Alarm
import com.meldcx.appscheduler.repository.AlarmRepository

class MainViewModel internal constructor(private val alarmRepository: AlarmRepository) :
    ViewModel() {
    var alarmsLiveData: LiveData<List<Alarm>> = alarmRepository.alarmsLiveData

    fun update(alarm: Alarm?) {
        alarmRepository.update(alarm)
    }

    fun delete(alarm: Alarm?) {
        Log.d("DeleteTest==>", "delete: ")
        alarmRepository.delete(alarm)
    }
}