package com.meldcx.appscheduler

import androidx.room.Room
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.meldcx.appscheduler.data.AppDatabase
import com.meldcx.appscheduler.data.CurrencyData
import com.meldcx.appscheduler.data.Rate
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
    fun testInsertCurrencyBaseInfo() {
        val currencyData = CurrencyData()
        currencyData.id = 1
        currencyData.base = "USD"
        taskDatabase?.currencyDao()?.insert(currencyData)
        val data = taskDatabase!!.currencyDao().currencyBase
        assertThat(data?.base, Is("USD"))
    }

    @Test
    fun testInsertCurrencyRatesInfo() {
        val rate = Rate("USD", 1.0)
        val rates:ArrayList<Rate> = ArrayList()
        rates.add(rate)
        taskDatabase?.currencyDao()?.insert(rates)
        val data = taskDatabase!!.currencyDao().rates
        assertThat(data.size, Is(1))
    }

    @Test
    fun testFetchCurrencyData() {
        val rates:ArrayList<Rate> = ArrayList()
        rates.add(Rate("USD", 1.0))
        rates.add(Rate("BDT", 87.0))
        taskDatabase?.currencyDao()?.insert(rates)
        val items = taskDatabase!!.currencyDao().rates
        assertThat(items?.size, Is(2))
    }

}