package com.meldcx.appscheduler.di

import android.content.Context
import com.meldcx.appscheduler.data.ScheduleDao
import dagger.Module
import dagger.Provides
import com.meldcx.appscheduler.di.scope.PerActivity
import com.meldcx.appscheduler.repository.ScheduleRepository
import com.meldcx.appscheduler.repository.AppListRepository
import com.meldcx.appscheduler.ui.applist.AppListAdapter
import com.meldcx.appscheduler.ui.applist.AppListViewModel
import com.meldcx.appscheduler.ui.createalarm.CreateAlarmViewModel
import com.meldcx.appscheduler.ui.main.AlarmRecyclerViewAdapter
import com.meldcx.appscheduler.ui.main.MainViewModel

/**
 * Created by nazmul 02/08/2021.
 */
@Module
class ActivityModule(private val context: Context) {

    @Provides
    fun provideAlarmRepository(alarmDao: ScheduleDao): ScheduleRepository {
        return ScheduleRepository(alarmDao)
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
    fun provideCreateAlarmViewModel(scheduleRepository: ScheduleRepository): CreateAlarmViewModel {
        return CreateAlarmViewModel(scheduleRepository)
    }

    @Provides
    @PerActivity
    fun provideAppListViewModel(appListRepository: AppListRepository): AppListViewModel {
        return AppListViewModel(appListRepository)
    }

    @Provides
    @PerActivity
    fun provideAlarmRecyclerViewAdapter(alarmListViewModel: MainViewModel): AlarmRecyclerViewAdapter {
        return AlarmRecyclerViewAdapter(alarmListViewModel)
    }

    @Provides
    @PerActivity
    fun provideAppListViewAdapter(alarmListViewModel: AppListViewModel): AppListAdapter {
        return AppListAdapter(alarmListViewModel)
    }

}