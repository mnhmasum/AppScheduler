package com.meldcx.appscheduler.repository

import android.content.pm.ApplicationInfo
import android.content.pm.PackageManager
import com.meldcx.appscheduler.data.AppItem
import com.meldcx.appscheduler.retrofit.Rates


class CurrencyListRepository(private val pm: PackageManager) : CurrencyListRepositoryInterface {

   /* override suspend fun getInstalledApps(): List<AppItem> {
        val list: ArrayList<AppItem> = ArrayList()
        val packages = pm.getInstalledApplications(PackageManager.GET_META_DATA)

        for (packageInfo in packages) {
            when {
                packageInfo.flags and ApplicationInfo.FLAG_UPDATED_SYSTEM_APP === 1 -> Unit
                packageInfo.flags and ApplicationInfo.FLAG_SYSTEM === 1 -> Unit
                else -> {
                    val appName = pm.getApplicationLabel(packageInfo).toString()
                    val appLauncher = packageInfo.packageName
                    if (!appLauncher.isNullOrEmpty()) {
                        list.add(AppItem(appName, appLauncher))
                    }
                }
            }
        }
        return list

    }*/

    override suspend fun getCurrencyRateList(): List<Rates> {
        val list: ArrayList<Rates> = ArrayList()
        return list
    }

}