package com.meldcx.appscheduler.utils

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import android.widget.TimePicker
import com.meldcx.appscheduler.broadcastreceiver.AlarmBroadcastReceiver
import com.meldcx.appscheduler.data.Schedule
import com.meldcx.appscheduler.databinding.ActivityCreatealarmBinding
import java.util.*

fun getTimePickerHour(resId: TimePicker): Int =
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
        resId.getHour()
    } else {
        resId.getCurrentHour()
    }

fun getTimePickerMinute(resId: TimePicker): Int =
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
        resId.minute
    } else {
        resId.currentMinute
    }

fun Context.buildTask(binding: ActivityCreatealarmBinding, appId: String): Schedule {
    return Schedule(
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

fun Context.enableIntervalAPICallAlarmService() {
    val alarmManager = getSystemService(Context.ALARM_SERVICE) as AlarmManager
    val intent = Intent(this, AlarmBroadcastReceiver::class.java)
    val alarmPendingIntent =
        PendingIntent.getBroadcast(this, Constant.ALARM_REQUEST_CODE, intent, 0)
    alarmManager.setInexactRepeating(
        AlarmManager.ELAPSED_REALTIME_WAKEUP,
        10000,
        10000, alarmPendingIntent
    )
    //AlarmManager.INTERVAL_HALF_HOUR
}


