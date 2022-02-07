package com.meldcx.appscheduler.di

import android.content.Context
import com.meldcx.appscheduler.data.AlarmDao
import dagger.Module
import dagger.Provides
import com.meldcx.appscheduler.di.scope.PerActivity
import com.meldcx.appscheduler.repository.AlarmRepository
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
    fun provideAlarmRepository(alarmDao: AlarmDao): AlarmRepository {
        return AlarmRepository(alarmDao)
    }

    @Provides
    fun provideAppListRepository(): AppListRepository {
        return AppListRepository(context.packageManager)
    }

    @Provides
    @PerActivity
    fun provideAlarmListViewModel(alarmRepository: AlarmRepository): MainViewModel {
        return MainViewModel(alarmRepository)
    }

    @Provides
    @PerActivity
    fun provideCreateAlarmViewModel(alarmRepository: AlarmRepository): CreateAlarmViewModel {
        return CreateAlarmViewModel(alarmRepository)
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