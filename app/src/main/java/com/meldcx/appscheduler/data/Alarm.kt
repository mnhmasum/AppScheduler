package com.meldcx.appscheduler.data

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.meldcx.appscheduler.broadcastreceiver.AlarmBroadcastReceiver
import com.meldcx.appscheduler.utils.Constant.Companion.APP_ID
import java.io.Serializable
import java.util.*

@Entity(tableName = "alarm_table")
class Alarm(
    @field:PrimaryKey var alarmId: Int,
    val hour: Int,
    val minute: Int,
    val title: String,
    val appId: String,
    var created: Long,
    var isStarted: Boolean,
    val isRecurring: Boolean,
    val isMonday: Boolean,
    val isTuesday: Boolean,
    val isWednesday: Boolean,
    val isThursday: Boolean,
    val isFriday: Boolean,
    val isSaturday: Boolean,
    val isSunday: Boolean
) : Serializable {
    fun schedule(context: Context) {
        val alarmManager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager
        val intent = Intent(context, AlarmBroadcastReceiver::class.java)
        intent.putExtra(AlarmBroadcastReceiver.ALARM_ID, alarmId)
        intent.putExtra(AlarmBroadcastReceiver.RECURRING, isRecurring)
        intent.putExtra(AlarmBroadcastReceiver.MONDAY, isMonday)
        intent.putExtra(AlarmBroadcastReceiver.TUESDAY, isTuesday)
        intent.putExtra(AlarmBroadcastReceiver.WEDNESDAY, isWednesday)
        intent.putExtra(AlarmBroadcastReceiver.THURSDAY, isThursday)
        intent.putExtra(AlarmBroadcastReceiver.FRIDAY, isFriday)
        intent.putExtra(AlarmBroadcastReceiver.SATURDAY, isSaturday)
        intent.putExtra(AlarmBroadcastReceiver.SUNDAY, isSunday)
        intent.putExtra(AlarmBroadcastReceiver.TITLE, title)
        intent.putExtra(APP_ID, appId)
        val alarmPendingIntent = PendingIntent.getBroadcast(context, alarmId, intent, 0)
        val calendar = Calendar.getInstance()
        calendar.timeInMillis = System.currentTimeMillis()
        calendar[Calendar.HOUR_OF_DAY] = hour
        calendar[Calendar.MINUTE] = minute
        calendar[Calendar.SECOND] = 0
        calendar[Calendar.MILLISECOND] = 0

        // if alarm time has already passed, increment day by 1
        if (calendar.timeInMillis <= System.currentTimeMillis()) {
            calendar[Calendar.DAY_OF_MONTH] = calendar[Calendar.DAY_OF_MONTH] + 1
        }
        if (!isRecurring) {
            alarmManager.setExact(
                AlarmManager.RTC_WAKEUP,
                calendar.timeInMillis,
                alarmPendingIntent
            )
        } else {
            val RUN_DAILY = (24 * 60 * 60 * 1000).toLong()
            alarmManager.setRepeating(
                AlarmManager.RTC_WAKEUP,
                calendar.timeInMillis,
                RUN_DAILY,
                alarmPendingIntent
            )
        }
        isStarted = true
    }

    fun cancelAlarm(context: Context) {
        val alarmManager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager
        val intent = Intent(context, AlarmBroadcastReceiver::class.java)
        val alarmPendingIntent = PendingIntent.getBroadcast(context, alarmId, intent, 0)
        alarmManager.cancel(alarmPendingIntent)
        isStarted = false
    }

}