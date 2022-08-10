package com.paypay.currencyconverter.utils

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import android.widget.TimePicker
import com.paypay.currencyconverter.broadcastreceiver.AlarmBroadcastReceiver

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


fun Context.enableIntervalAPICallAlarmService() {
    val alarmManager = getSystemService(Context.ALARM_SERVICE) as AlarmManager
    val intent = Intent(this, AlarmBroadcastReceiver::class.java)
    val alarmPendingIntent =
        PendingIntent.getBroadcast(this, Constant.ALARM_REQUEST_CODE, intent, 0)
    alarmManager.setInexactRepeating(
        AlarmManager.ELAPSED_REALTIME_WAKEUP,
        AlarmManager.INTERVAL_HALF_HOUR,
        AlarmManager.INTERVAL_HALF_HOUR, alarmPendingIntent
    )
    //AlarmManager.INTERVAL_HALF_HOUR
}


