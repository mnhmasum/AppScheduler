package com.meldcx.appscheduler.data

sealed class AppListIntent {
    object FetchApps : AppListIntent()
    object SelectApp : AppListIntent()
    object Loading : AppListIntent()
}