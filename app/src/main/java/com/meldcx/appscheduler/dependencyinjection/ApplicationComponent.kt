package com.meldcx.appscheduler.dependencyinjection

import dagger.Component
import com.meldcx.appscheduler.application.MainApplication
import com.meldcx.appscheduler.data.CurrencyDao
import javax.inject.Singleton

@Singleton
@Component(modules = [ApplicationModule::class])
interface ApplicationComponent {
    fun inject(app: MainApplication)
    fun exposeCurrencyDao(): CurrencyDao
}