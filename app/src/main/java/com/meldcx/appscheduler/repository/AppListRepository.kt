package com.meldcx.appscheduler.repository

import android.content.pm.ApplicationInfo
import android.content.pm.PackageManager
import com.meldcx.appscheduler.data.AppItem


class AppListRepository(private val pm: PackageManager) {

    fun getApps(): List<AppItem> {
        val list: ArrayList<AppItem> = ArrayList()
        val packages = pm.getInstalledApplications(PackageManager.GET_META_DATA)
        for (packageInfo in packages) {
            if (packageInfo.flags and ApplicationInfo.FLAG_UPDATED_SYSTEM_APP === 1) {
            } else if (packageInfo.flags and ApplicationInfo.FLAG_SYSTEM === 1) {
            } else {
                val appName = pm.getApplicationLabel(packageInfo).toString()
                val appLauncher = packageInfo.packageName
                if (appLauncher != null) {
                    val appItem = AppItem(appName, appLauncher)
                    list.add(appItem)
                }
            }
        }
        return list

    }

}