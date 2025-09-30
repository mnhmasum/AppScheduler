package com.meldcx.appscheduler.application

import android.app.Application
import com.meldcx.appscheduler.data.AppDatabase
import com.meldcx.appscheduler.di.ApplicationComponent
import com.meldcx.appscheduler.di.ApplicationModule
import com.meldcx.appscheduler.di.DaggerApplicationComponent
import javax.inject.Inject

class MainApplication : Application() {

    companion object {
        lateinit var appDatabase: AppDatabase
    }

    @Inject
    lateinit var appDatabase: AppDatabase
    lateinit var applicationComponent: ApplicationComponent

    override fun onCreate() {
        super.onCreate()
        applicationComponent = DaggerApplicationComponent.builder().applicationModule(ApplicationModule(this)).build()
        applicationComponent.inject(this)
        Companion.appDatabase = appDatabase
    }

}