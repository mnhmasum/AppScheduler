package com.meldcx.appscheduler.repository

import com.meldcx.appscheduler.data.AppItem


interface AppListRepositoryInterface {
    suspend fun getInstalledApps(): List<AppItem>
}