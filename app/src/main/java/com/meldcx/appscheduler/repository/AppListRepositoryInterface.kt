package com.meldcx.appscheduler.repository

import android.content.pm.ApplicationInfo
import android.content.pm.PackageManager
import com.meldcx.appscheduler.data.AppItem


interface AppListRepositoryInterface {
    suspend fun getInstalledApps(): List<AppItem>
}