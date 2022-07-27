package com.meldcx.appscheduler.repository

import com.meldcx.appscheduler.data.AppItem
import com.meldcx.appscheduler.ui.applist.WalletViewState


interface AppListRepositoryInterface {
    suspend fun getInstalledApps(): List<AppItem>
    suspend fun getAppItemTest(): List<AppItem>
}