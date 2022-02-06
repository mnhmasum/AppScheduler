package com.meldcx.appscheduler.ui.createalarm

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.meldcx.appscheduler.data.Alarm
import com.meldcx.appscheduler.repository.AlarmRepository
import com.meldcx.appscheduler.utils.Errors

class CreateAlarmViewModel internal constructor(private val alarmRepository: AlarmRepository) : ViewModel() {
    private val errorList = ArrayList<Errors>()
    var errors = MutableLiveData<MutableList<Errors>>()

    fun insert(alarm: Alarm?) {
        alarmRepository.insert(alarm)
    }

    fun isTaskValid(alarm: Alarm): Boolean {
        errorList.clear()
        if (alarm.appId.isEmpty()) {
            errorList.add(Errors.MISSING_APP)
            errors.value = errorList
        }
        return errors.value?.isEmpty()!!
    }

}