package com.meldcx.appscheduler.di

import dagger.Component
import com.meldcx.appscheduler.application.MainApplication
import javax.inject.Singleton

@Singleton
@Component(modules = [ApplicationModule::class])
interface ApplicationComponent {
    fun inject(app: MainApplication)
}