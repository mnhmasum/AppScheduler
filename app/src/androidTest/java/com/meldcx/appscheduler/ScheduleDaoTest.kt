package com.meldcx.appscheduler

import androidx.room.Room
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.meldcx.appscheduler.data.Schedule
import com.meldcx.appscheduler.data.AppDatabase
import org.junit.After
import org.junit.Assert.assertThat
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.hamcrest.CoreMatchers.`is` as Is


@RunWith(AndroidJUnit4::class)
class ScheduleDaoTest {
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
    fun testInsertSchedule() {
        val entity = Schedule(
            123,
            1,
            1,
            "Test",
            "com.test.app",
            1L,
            false,
            false,
            false,
            false,
            false,
            false,
            false,
            false,
            false
        )
        taskDatabase!!.alarmDao().insert(entity)
        val taskList = LiveDataTestUtil.getValue(taskDatabase!!.alarmDao().schedules)
        assertThat(taskList!![0].alarmId, Is(123))
        assertThat(taskList.size, Is(1))
    }

}