package com.meldcx.appscheduler.application

import android.app.Application
import com.meldcx.appscheduler.data.AppDatabase
import com.meldcx.appscheduler.dependencyinjection.ApplicationComponent
import com.meldcx.appscheduler.dependencyinjection.ApplicationModule
import com.meldcx.appscheduler.dependencyinjection.DaggerApplicationComponent
import com.meldcx.appscheduler.retrofit.initRetrofit
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
        initRetrofit(this)
        applicationComponent = DaggerApplicationComponent.builder().applicationModule(ApplicationModule(this)).build()
        applicationComponent.inject(this)
        Companion.appDatabase = appDatabase
    }

}