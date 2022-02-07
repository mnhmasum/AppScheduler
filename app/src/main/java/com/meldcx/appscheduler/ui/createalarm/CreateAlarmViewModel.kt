package com.meldcx.appscheduler.ui.createalarm

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.meldcx.appscheduler.data.Alarm
import com.meldcx.appscheduler.repository.AlarmRepository
import com.meldcx.appscheduler.utils.Errors

class CreateAlarmViewModel internal constructor(private val repository: AlarmRepository) :
    ViewModel() {
    private val errorList = ArrayList<Errors>()
    var validationErrors = MutableLiveData<MutableList<Errors>>()

    fun insert(alarm: Alarm?) {
        repository.insert(alarm)
    }

    fun isTaskValid(alarm: Alarm): Boolean {
        errorList.clear()
        if (alarm.appId.isEmpty()) {
            errorList.add(Errors.MISSING_APP)
            validationErrors.value = errorList
        }
        return validationErrors.value == null || validationErrors.value?.isEmpty()!!
    }

}