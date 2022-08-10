package com.meldcx.appscheduler

import com.meldcx.appscheduler.data.ExchangeRate
import com.meldcx.appscheduler.utils.ConverterUtil
import org.junit.Test

import org.junit.Assert.*

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ConverterUtilTest {
    @Test
    fun convert_is_correct() {
        val result = ConverterUtil.convertToBaseRate(94.62, 79.65, 1.0);
        assertEquals(0.842, result, 0.1)
    }

    @Test
    fun converted_list_correct() {
        val base = 1.0
        val inputValue = 10.0

        val exchangeRateList: ArrayList<ExchangeRate> = ArrayList()
        exchangeRateList.add(ExchangeRate("USD", 1.0))
        exchangeRateList.add(ExchangeRate("BDT", 94.62))

        val result = ConverterUtil.convertValueAndGet(base, inputValue, exchangeRateList) as ArrayList
        assertEquals(result.size, 2)
        assertEquals(result[0].rate!!, 10.0, 0.1)
        assertEquals(result[1].rate!!, 946.2, 0.1)
    }
}