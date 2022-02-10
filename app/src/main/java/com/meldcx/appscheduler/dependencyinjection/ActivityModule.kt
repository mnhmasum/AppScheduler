package com.meldcx.appscheduler.dependencyinjection

import android.content.Context
import com.meldcx.appscheduler.data.ScheduleDao
import dagger.Module
import dagger.Provides
import com.meldcx.appscheduler.dependencyinjection.scope.PerActivity
import com.meldcx.appscheduler.repository.ScheduleRepository
import com.meldcx.appscheduler.repository.AppListRepository
import com.meldcx.appscheduler.repository.CreateTaskRepository
import com.meldcx.appscheduler.ui.applist.AppListAdapter
import com.meldcx.appscheduler.ui.applist.AppListViewModel
import com.meldcx.appscheduler.ui.create.CreateScheduleViewModel
import com.meldcx.appscheduler.ui.main.MainViewAdapter
import com.meldcx.appscheduler.ui.main.MainViewModel

/**
 * Created by nazmul 01/22/2022.
 */
@Module
class ActivityModule(private val context: Context) {

    @Provides
    fun provideAlarmRepository(alarmDao: ScheduleDao): ScheduleRepository {
        return ScheduleRepository(alarmDao)
    }

    @Provides
    fun provideCreateTaskRepository(alarmDao: ScheduleDao): CreateTaskRepository {
        return CreateTaskRepository(alarmDao)
    }

    @Provides
    fun provideAppListRepository(): AppListRepository {
        return AppListRepository(context.packageManager)
    }

    @Provides
    @PerActivity
    fun provideAlarmListViewModel(scheduleRepository: ScheduleRepository): MainViewModel {
        return MainViewModel(scheduleRepository)
    }

    @Provides
    @PerActivity
    fun provideCreateAlarmViewModel(createTaskRepository: CreateTaskRepository): CreateScheduleViewModel {
        return CreateScheduleViewModel(createTaskRepository)
    }

    @Provides
    @PerActivity
    fun provideAppListViewModel(appListRepository: AppListRepository): AppListViewModel {
        return AppListViewModel(appListRepository)
    }

    @Provides
    @PerActivity
    fun provideAlarmRecyclerViewAdapter(alarmListViewModel: MainViewModel): MainViewAdapter {
        return MainViewAdapter(alarmListViewModel)
    }

    @Provides
    @PerActivity
    fun provideAppListViewAdapter(alarmListViewModel: AppListViewModel): AppListAdapter {
        return AppListAdapter(alarmListViewModel)
    }

}