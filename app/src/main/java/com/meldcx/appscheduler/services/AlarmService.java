package com.meldcx.appscheduler.services;

import android.app.Service;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.IBinder;

import androidx.annotation.Nullable;

import static com.meldcx.appscheduler.broadcastreceiver.AlarmBroadcastReceiver.TITLE;

public class AlarmService extends Service {

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        String title = intent.getStringExtra(TITLE);
        PackageManager pm = getApplicationContext().getPackageManager();
        Intent intentNew = pm.getLaunchIntentForPackage(title);
        intentNew.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        try {
            startActivity(intentNew);
        } catch (Exception exception) {
            exception.printStackTrace();
        }
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
