package com.meldcx.appscheduler.ui.create

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.meldcx.appscheduler.data.Schedule
import com.meldcx.appscheduler.repository.CreateTaskRepositoryInterface
import com.meldcx.appscheduler.utils.Constant.Errors
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class CreateScheduleViewModel internal constructor(private val repository: CreateTaskRepositoryInterface) : ViewModel() {
    private val errorList = ArrayList<Errors>()
    var validationErrors = MutableLiveData<MutableList<Errors>>()

    fun insert(schedule: Schedule) = viewModelScope.launch(Dispatchers.IO) {
        repository.insert(schedule)
    }

    fun isTaskValid(schedule: Schedule): Boolean {
        errorList.clear()
        if (schedule.appId.isEmpty()) {
            errorList.add(Errors.MISSING_APP)
            validationErrors.value = errorList
        }
        return validationErrors.value == null || validationErrors.value?.isEmpty()!!
    }

}