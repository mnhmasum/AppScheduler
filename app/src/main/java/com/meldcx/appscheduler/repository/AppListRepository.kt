package com.meldcx.appscheduler.repository

import android.content.pm.PackageManager
import androidx.lifecycle.MutableLiveData
import com.meldcx.appscheduler.application.MainApplication.Companion.databaseWriteExecutor
import com.meldcx.appscheduler.data.AppItem


class AppListRepository(private val pm: PackageManager) {
    companion object {
        private const val TAG = "AppListRepository`"
    }

    val alarmsLiveData = MutableLiveData<List<AppItem>>()
    fun getApps(): MutableLiveData<List<AppItem>> {
        val list: ArrayList<AppItem> = ArrayList()
        val packages = pm.getInstalledApplications(PackageManager.GET_META_DATA)
        databaseWriteExecutor.execute {
            for (packageInfo in packages) {
                val appItem = AppItem(
                    packageInfo.packageName,
                    pm.getLaunchIntentForPackage(packageInfo.packageName).toString()
                )
                list.add(appItem)
            }
        }
        alarmsLiveData.value = list

        return alarmsLiveData

    }

}