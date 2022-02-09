package com.meldcx.appscheduler.dependencytinjection

import dagger.Component
import com.meldcx.appscheduler.dependencytinjection.scope.PerActivity
import com.meldcx.appscheduler.ui.applist.AppListActivity
import com.meldcx.appscheduler.ui.createalarm.CreateAlarmActivity
import com.meldcx.appscheduler.ui.main.MainActivity

@PerActivity
@Component(dependencies = [ApplicationComponent::class], modules = [ActivityModule::class])
interface MainActivityComponent {
    fun inject(mainActivity: MainActivity)
    fun inject(createAlarmActivity: CreateAlarmActivity)
    fun inject(appListActivity: AppListActivity)
}