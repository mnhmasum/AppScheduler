package com.meldcx.test.utils

import android.content.Context
import android.os.Build
import android.widget.TimePicker
import com.meldcx.appscheduler.data.Alarm
import com.meldcx.appscheduler.databinding.ActivityCreatealarmBinding
import java.util.*

fun getTimePickerHour(resId: TimePicker):Int = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
        resId.getHour()
    } else {
        resId.getCurrentHour()
    }

fun getTimePickerMinute(resId: TimePicker):Int = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
    resId.minute
} else {
    resId.currentMinute
}

fun Context.buildTask(binding: ActivityCreatealarmBinding, appId:String): Alarm {
    return Alarm(
        Random().nextInt(Int.MAX_VALUE),
        getTimePickerHour(binding.timePicker),
        getTimePickerMinute(binding.timePicker),
        binding.textAppPackageName.text.toString(),
        appId,
        System.currentTimeMillis(),
        true,
        binding.checkRecurring.isChecked,
        binding.checkMon.isChecked,
        binding.checkTue.isChecked,
        binding.checkWed.isChecked,
        binding.checkThu.isChecked,
        binding.checkFri.isChecked,
        binding.checkSat.isChecked,
        binding.checkSun.isChecked
    )
}



