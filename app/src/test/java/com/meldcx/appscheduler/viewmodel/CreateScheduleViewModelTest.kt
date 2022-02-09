package com.meldcx.appscheduler.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.google.common.truth.Truth.assertThat
import com.meldcx.appscheduler.LiveDataUtil
import com.meldcx.appscheduler.MainCoroutineRule
import com.meldcx.appscheduler.data.Schedule
import com.meldcx.appscheduler.repo.FakeCreateTaskRepository
import com.meldcx.appscheduler.ui.createalarm.CreateScheduleViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class CreateScheduleViewModelTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    private lateinit var viewModel: CreateScheduleViewModel

    @Before
    fun setup() {
        viewModel = CreateScheduleViewModel(FakeCreateTaskRepository())
    }

    @Test
    fun `validation task create test`() {
        val schedule = getSchedule()
        viewModel.isTaskValid(schedule)
        val value = LiveDataUtil.getValue(viewModel.validationErrors)
        assertThat(value).isEqualTo(null)
    }

    fun getSchedule() = Schedule(
        123,
        1,
        1,
        "Test",
        "com.app.test",
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