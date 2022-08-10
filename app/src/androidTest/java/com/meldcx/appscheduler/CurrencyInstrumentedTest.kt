package com.meldcx.appscheduler

import android.app.Application
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.meldcx.appscheduler.application.MainApplication
import com.meldcx.appscheduler.retrofit.apiClient
import com.meldcx.appscheduler.retrofit.initRetrofit
import org.junit.Assert

import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Assert.*

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class CurrencyInstrumentedTest {
    @Test
    fun useAppContext() {
        // Context of the app under test.
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext
        initRetrofit(appContext.applicationContext as Application)
        val client = apiClient()
        assertNotNull(client)
        assertEquals("com.meldcx.appscheduler", appContext.packageName)
    }
}