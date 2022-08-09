package com.meldcx.appscheduler.broadcastreceiver

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import android.util.Log
import com.meldcx.appscheduler.application.MainApplication
import com.meldcx.appscheduler.repository.CurrencyDataRepository
import com.meldcx.appscheduler.services.RescheduleAlarmsService
import com.meldcx.appscheduler.utils.Constant.Companion.APP_ID
import com.meldcx.appscheduler.utils.enableIntervalAPICallAlarmService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.util.*


class AlarmBroadcastReceiver : BroadcastReceiver() {
    lateinit var context: Context
    override fun onReceive(context: Context, intent: Intent) {
        this.context = context
        if (Intent.ACTION_BOOT_COMPLETED == intent.action) {
            context.enableIntervalAPICallAlarmService()
        } else {
            GlobalScope.launch(Dispatchers.IO) {
                val repository = CurrencyDataRepository(MainApplication.appDatabase.currencyDao())
                repository.getCurrencyData()
                Log.d("Alarm Fired", "onReceive: Yes fired up")
            }
        }
    }

    private fun alarmIsToday(intent: Intent): Boolean {
        val calendar = Calendar.getInstance()
        calendar.timeInMillis = System.currentTimeMillis()
        val today = calendar[Calendar.DAY_OF_WEEK]

        return when (today) {
            Calendar.MONDAY -> intent.getBooleanExtra(MONDAY, false)
            Calendar.TUESDAY -> intent.getBooleanExtra(TUESDAY, false)
            Calendar.WEDNESDAY -> intent.getBooleanExtra(WEDNESDAY, false)
            Calendar.THURSDAY -> intent.getBooleanExtra(THURSDAY, false)
            Calendar.FRIDAY -> intent.getBooleanExtra(FRIDAY, false)
            Calendar.SATURDAY -> intent.getBooleanExtra(SATURDAY, false)
            Calendar.SUNDAY -> intent.getBooleanExtra(SUNDAY, false)
            else -> false
        }
    }

    private fun startAlarmService(context: Context, intent: Intent) {
        val appId = intent.getStringExtra(APP_ID)
        val pm: PackageManager = context.getPackageManager()
        val intentNew = pm.getLaunchIntentForPackage(appId!!)
        intentNew!!.flags = Intent.FLAG_ACTIVITY_NEW_TASK
        try {
            context.startActivity(intentNew)
        } catch (exception: Exception) {
            exception.printStackTrace()
        }
    }

    private fun startRescheduleAlarmsService(context: Context) {
        val intentService = Intent(context, RescheduleAlarmsService::class.java)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            context.startForegroundService(intentService)
        } else {
            context.startService(intentService)
        }
    }

    companion object {
        const val MONDAY = "MONDAY"
        const val TUESDAY = "TUESDAY"
        const val WEDNESDAY = "WEDNESDAY"
        const val THURSDAY = "THURSDAY"
        const val FRIDAY = "FRIDAY"
        const val SATURDAY = "SATURDAY"
        const val SUNDAY = "SUNDAY"
        const val RECURRING = "RECURRING"
        const val TITLE = "TITLE"
        const val ALARM_ID = "ALARM_ID"
    }
}