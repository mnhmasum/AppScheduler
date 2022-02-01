package com.meldcx.appscheduler.di

import android.app.Activity
import android.content.Context
import android.content.pm.PackageManager
import dagger.Module
import dagger.Provides
import com.meldcx.appscheduler.di.scope.PerActivity
import com.meldcx.appscheduler.repository.AlarmRepository
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
    fun provideAlarmRepository(): AlarmRepository {
        return AlarmRepository()
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
    fun provideAppListViewModel(): AppListViewModel {
        val pm:PackageManager = context.packageManager
        return AppListViewModel(pm)
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

    /*  @Provides
      fun provideAppInfoNew(appInfo: AppInfo): AppInfoNew {
          return AppInfoNew(appInfo)
      }*/
}