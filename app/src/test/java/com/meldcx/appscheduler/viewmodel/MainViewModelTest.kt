package com.meldcx.appscheduler.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.google.common.truth.Truth.assertThat
import com.meldcx.appscheduler.LiveDataUtil
import com.meldcx.appscheduler.MainCoroutineRule
import com.meldcx.appscheduler.data.Schedule
import com.meldcx.appscheduler.repo.FakeScheduleRepository
import com.meldcx.appscheduler.ui.main.MainViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class MainViewModelTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    private lateinit var viewModel: MainViewModel

    @Before
    fun setup() {
        viewModel = MainViewModel(FakeScheduleRepository())
    }

    @Test
    fun `insert schedule test`() {
        val schedule = schedule()
        viewModel.insert(schedule)
        Thread.sleep(1000)
        val value = LiveDataUtil.getValue(viewModel.scheduleLiveData)
        Assert.assertEquals(value?.size, 1)
        assertThat(value).contains(schedule)
    }

    @Test
    fun `update schedule test`() {
        val schedule = schedule()
        viewModel.insert(schedule)
        schedule.alarmId = 100
        viewModel.update(schedule)
        val value = LiveDataUtil.getValue(viewModel.scheduleLiveData)
        Assert.assertEquals(value?.get(0)!!.alarmId, 100)
        Assert.assertEquals(value.isEmpty(), false)

    }

    @Test
    fun `delete schedule test`() {
        val schedule = schedule()
        viewModel.insert(schedule)
        viewModel.delete(schedule)
        val value = LiveDataUtil.getValue(viewModel.scheduleLiveData)
        Assert.assertEquals(value?.size, 0)
    }

    fun schedule() = Schedule(
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
}