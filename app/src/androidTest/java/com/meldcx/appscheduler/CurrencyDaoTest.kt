package com.meldcx.appscheduler

import androidx.room.Room
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.meldcx.appscheduler.data.AppDatabase
import com.meldcx.appscheduler.data.CurrencyData
import org.junit.After
import org.junit.Assert.assertThat
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.hamcrest.CoreMatchers.`is` as Is


@RunWith(AndroidJUnit4::class)
class CurrencyDaoTest {
    private var taskDatabase: AppDatabase? = null

    @Before
    fun init() {
        taskDatabase = Room.inMemoryDatabaseBuilder(
            InstrumentationRegistry.getInstrumentation().context,
            AppDatabase::class.java
        ).build()
    }

    @After
    fun close() {
        taskDatabase!!.close()
    }

    @Test
    fun testLoadData() {
        val liveData = taskDatabase!!.alarmDao().schedules
        val items = LiveDataTestUtil.getValue(liveData)
        assertThat(items?.size, Is(1))
    }

    @Test
    fun testInsertCurrencyBaseInfo() {
        val currencyData = CurrencyData()
        currencyData.id = 1
        currencyData.base = "USD"
        taskDatabase?.currencyDao()?.insert(currencyData)
        val data = taskDatabase!!.currencyDao().currencyBase
        assertThat(data?.base, Is("USD"))
        //assertThat(taskList.size, Is(1))
    }

}