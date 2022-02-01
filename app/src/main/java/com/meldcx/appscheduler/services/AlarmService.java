package com.meldcx.appscheduler.services;

import android.app.Service;
import android.content.ComponentName;
import android.content.Intent;
import android.os.IBinder;

import androidx.annotation.Nullable;

import static com.meldcx.appscheduler.broadcastreceiver.AlarmBroadcastReceiver.ALARMID;
import static com.meldcx.appscheduler.broadcastreceiver.AlarmBroadcastReceiver.TITLE;


public class AlarmService extends Service {

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        String title = intent.getStringExtra(TITLE);
        String alarmId = intent.getStringExtra(ALARMID);

        ComponentName cName = new ComponentName("com.napcosecurity.iBridge2.gemini", title);
        Intent intentNew = new Intent("android.intent.action.MAIN");
        intentNew.setComponent(cName);
        intentNew.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intentNew);
        return START_STICKY;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
