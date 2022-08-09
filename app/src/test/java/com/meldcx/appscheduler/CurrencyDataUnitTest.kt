package com.meldcx.appscheduler

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
}