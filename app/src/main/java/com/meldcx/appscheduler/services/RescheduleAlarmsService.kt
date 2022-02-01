package com.meldcx.appscheduler.services

import android.content.Intent
import android.os.IBinder
import androidx.lifecycle.LifecycleService
import com.meldcx.appscheduler.data.Alarm
import com.meldcx.appscheduler.repository.AlarmRepository

class RescheduleAlarmsService : LifecycleService() {
    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        super.onStartCommand(intent, flags, startId)
        val alarmRepository = AlarmRepository()
        alarmRepository.alarmsLiveData.observe(this, { alarms: List<Alarm> ->
            for (alarm in alarms) {
                if (alarm.isStarted) {
                    alarm.schedule(applicationContext)
                }
            }
        })
        return START_STICKY
    }

    override fun onBind(intent: Intent): IBinder? {
        super.onBind(intent)
        return null
    }
}