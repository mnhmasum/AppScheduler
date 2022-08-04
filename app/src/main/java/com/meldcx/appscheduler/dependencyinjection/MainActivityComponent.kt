package com.meldcx.appscheduler.dependencyinjection

import dagger.Component
import com.meldcx.appscheduler.dependencyinjection.scope.PerActivity
import com.meldcx.appscheduler.ui.applist.AppListActivity
import com.meldcx.appscheduler.ui.create.CreateAlarmActivity
import com.meldcx.appscheduler.ui.currency.CurrencyActivity
import com.meldcx.appscheduler.ui.main.MainActivity

@PerActivity
@Component(dependencies = [ApplicationComponent::class], modules = [ActivityModule::class])
interface MainActivityComponent {
    fun inject(mainActivity: MainActivity)
    fun inject(createAlarmActivity: CreateAlarmActivity)
    fun inject(appListActivity: AppListActivity)
    fun inject(currencyActivity: CurrencyActivity)
}