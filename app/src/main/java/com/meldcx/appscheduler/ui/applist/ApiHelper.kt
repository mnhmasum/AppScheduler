package com.meldcx.appscheduler.ui.applist

import com.meldcx.appscheduler.data.AppItem

interface ApiHelper {

    suspend fun getApps(): List<AppItem>

}