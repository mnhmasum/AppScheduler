package com.meldcx.appscheduler.broadcastreceiver

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log
import com.meldcx.appscheduler.application.MainApplication
import com.meldcx.appscheduler.repository.CurrencyConverterRepository
import com.meldcx.appscheduler.utils.enableIntervalAPICallAlarmService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch


class AlarmBroadcastReceiver : BroadcastReceiver() {
    lateinit var context: Context
    override fun onReceive(context: Context, intent: Intent) {
        this.context = context
        if (Intent.ACTION_BOOT_COMPLETED == intent.action) {
            context.enableIntervalAPICallAlarmService()
        } else {
            GlobalScope.launch(Dispatchers.IO) {
                val repository = CurrencyConverterRepository(MainApplication.appDatabase.currencyDao())
                repository.getCurrencyData()
                Log.d("Alarm Fired", "onReceive: Yes fired up")
            }
        }
    }
}