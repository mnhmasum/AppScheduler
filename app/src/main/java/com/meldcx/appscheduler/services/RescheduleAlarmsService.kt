package com.meldcx.appscheduler.services

import android.content.Intent
import android.os.IBinder
import androidx.lifecycle.LifecycleService
import com.meldcx.appscheduler.application.MainApplication
import com.meldcx.appscheduler.data.Schedule
import com.meldcx.appscheduler.repository.ScheduleRepository

class RescheduleAlarmsService : LifecycleService() {
    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        super.onStartCommand(intent, flags, startId)
        val alarmRepository = ScheduleRepository(MainApplication.appDatabase.alarmDao())
        alarmRepository.alarmsLiveData.observe(this, { schedules: List<Schedule> ->
            for (alarm in schedules) {
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