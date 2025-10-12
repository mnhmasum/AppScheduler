package com.meldcx.appscheduler.repository

import com.meldcx.appscheduler.data.AppItem


interface AppListRepositoryImpl {
    suspend fun getInstalledApps(): List<AppItem>
}