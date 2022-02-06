package com.meldcx.appscheduler.ui.createalarm

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.meldcx.appscheduler.data.Alarm
import com.meldcx.appscheduler.repository.AlarmRepository
import com.meldcx.appscheduler.utils.Errors

class CreateAlarmViewModel internal constructor(private val alarmRepository: AlarmRepository) : ViewModel() {
    var validationErrors = MutableLiveData<MutableList<Errors>>()

    fun insert(alarm: Alarm?) {
        alarmRepository.insert(alarm)
    }

    fun isTaskValid(alarm: Alarm): Boolean {
        val errorList = ArrayList<Errors>()
        if (alarm.appId.isEmpty()) {
            errorList.add(Errors.MISSING_APP)
            validationErrors.value = errorList
        }
        return validationErrors.value?.isEmpty()!!
    }

}