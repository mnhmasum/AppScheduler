package com.meldcx.appscheduler.ui.applist

import android.content.pm.PackageManager
import android.util.Log
import com.meldcx.appscheduler.application.MainApplication
import com.meldcx.appscheduler.data.AppItem
import com.meldcx.appscheduler.repository.AppListRepository
import kotlinx.coroutines.Delay
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class ApiHelperImpl() : ApiHelper {
    lateinit var flow: Flow<List<AppItem>>
    override suspend fun getApps(): List<AppItem> {

        //val pm: PackageManager = getPackageManager()
//get a list of installed apps.
//get a list of installed apps.
        /*val packages = pm.getInstalledApplications(PackageManager.GET_META_DATA)

        val list:ArrayList<AppItem> = ArrayList()
        MainApplication.databaseWriteExecutor.execute {
            for (packageInfo in packages) {
                val appItem = AppItem(packageInfo.packageName, pm.getLaunchIntentForPackage(packageInfo.packageName).toString())
                //Log.d(AppListRepository.TAG, "Installed package :" + packageInfo.packageName)
                //Log.d(AppListRepository.TAG, "Source dir : " + packageInfo.sourceDir)
                //Log.d(AppListRepository.TAG, "Launch Activity :" + pm.getLaunchIntentForPackage(packageInfo.packageName))
                appItem.appId = packageInfo.packageName
                appItem.appLauncher = pm.getLaunchIntentForPackage(packageInfo.packageName).toString()
                list.add(appItem)
            }
        }*/
        /*flow = flow {
            //Log.d(TAG, "Start flow")

        }.flowOn(Dispatchers.Default)*/

        flow<List<AppItem>> {
            Thread.sleep(5000)
            val list: ArrayList<AppItem> = ArrayList()
            val appItem = AppItem("t", "y")
            list.add(appItem)
            emit(list)
        }.flowOn(Dispatchers.Default)

        val list: ArrayList<AppItem> = ArrayList()
        return list
    }
}