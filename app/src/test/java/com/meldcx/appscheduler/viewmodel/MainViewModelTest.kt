package com.meldcx.appscheduler.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.meldcx.appscheduler.LiveDataUtil
import com.meldcx.appscheduler.MainCoroutineRule
import com.meldcx.appscheduler.data.Schedule
import com.meldcx.appscheduler.repo.FakeArtRepository
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
        viewModel = MainViewModel(FakeArtRepository())
    }

    @Test
    fun `insert art without year returns error`() {
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
        viewModel.insert(entity)
        entity.alarmId = 128
        viewModel.update(entity)

        val value = LiveDataUtil.getValue(viewModel.scheduleLiveData)
        Assert.assertEquals(value[0].alarmId, 125)
        Assert.assertEquals(value.isEmpty(), false)
    }


    @Test
    fun `insert art without name returns error`() {
        //viewModel.makeArt("","Da Vinci","1500")

        //val value = viewModel.insertArtMessage.getOrAwaitValueTest()

        //assertThat(value.status).isEqualTo(Status.ERROR)
    }

    @Test
    fun `insert art without artistName returns error`() {
        //viewModel.makeArt("Mona Lisa","","1500")

        //val value = viewModel.insertArtMessage.getOrAwaitValueTest()

        //assertThat(value.status).isEqualTo(Status.ERROR)
    }
}