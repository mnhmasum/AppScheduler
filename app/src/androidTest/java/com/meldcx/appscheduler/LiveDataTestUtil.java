package com.meldcx.appscheduler;


import android.os.Handler;
import android.os.Looper;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

public class LiveDataTestUtil {
    public static <T> T getValue(final LiveData<T> liveData) throws InterruptedException {
        final Object[] data = new Object[1];
        final CountDownLatch latch = new CountDownLatch(1);
        Observer<T> observer = new Observer<T>() {
            @Override
            public void onChanged(T o) {
                data[0] = o;
                latch.countDown();
                liveData.removeObserver(this);
            }
        };

        Handler handler = new Handler(Looper.getMainLooper());
        handler.post(() -> liveData.observeForever(observer));
        latch.await(2, TimeUnit.SECONDS);
        //noinspection unchecked
        return (T) data[0];
    }
}