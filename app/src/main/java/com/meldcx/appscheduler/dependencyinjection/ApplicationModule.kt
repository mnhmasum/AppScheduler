package com.meldcx.appscheduler.dependencyinjection

import android.app.Application
import androidx.room.Room
import com.meldcx.appscheduler.data.ScheduleDao
import dagger.Module
import dagger.Provides
import com.meldcx.appscheduler.data.AppDatabase
import com.meldcx.appscheduler.dependencyinjection.qualifier.DatabaseName
import javax.inject.Singleton

@Module
class ApplicationModule(private val mApplication: Application) {

    @Provides
    @DatabaseName
    fun provideDatabaseName(): String {
        return "db-meldcx"
    }

    @Provides
    fun provideDatabase(@DatabaseName dbName: String): AppDatabase {
        return Room.databaseBuilder(mApplication, AppDatabase::class.java, dbName).build()
    }

    @Provides
    @Singleton
    fun provideAlarmDao(appDatabase: AppDatabase): ScheduleDao {
        return appDatabase.alarmDao()
    }

    @Provides
    @Singleton
    fun provideApplication(): Application {
        return mApplication
    }

}