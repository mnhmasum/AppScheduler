package com.meldcx.appscheduler.ui.applist

import android.content.pm.PackageManager
import android.util.Log
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.meldcx.appscheduler.di.MainActivityComponent
import com.meldcx.appscheduler.ui.base.BaseActivity
import dev.ronnie.imageloaderdagger2.R
import dev.ronnie.imageloaderdagger2.databinding.ActivityAppListBinding
import javax.inject.Inject

class AppListActivity : BaseActivity<ActivityAppListBinding>() {
    @Inject
    lateinit var appListViewModel: AppListViewModel

    @Inject
    lateinit var appListAdapter: AppListAdapter

    override fun getLayoutResource(): Int {
        return R.layout.activity_app_list
    }

    override fun initComponents() {
        val pm: PackageManager = packageManager
        val appListViewModel = ViewModelProviders.of(
            this, ViewModelFactory(pm)).get(AppListViewModel::class.java)
        binding.apply {
            viewModel = appListViewModel
            //adapter = appListAdapter
            activity = this@AppListActivity
        }

        appListViewModel.mutableLiveData?.observe(this, Observer {
            for (i in it) {
                Log.d("All APPS", "initComponents: " + i.appLauncher)
            }
        })

        /*lifecycleScope.launch {
            appListViewModel.userIntent.send(MainIntent.FetchUser)
        }

        lifecycleScope.launch {
            appListViewModel.state.collect {
                when (it) {
                    is MainState.Idle-> Toast.makeText(this@AppListActivity, "ideal", Toast.LENGTH_SHORT).show()
                    is MainState.Loading-> Toast.makeText(this@AppListActivity, "Loading", Toast.LENGTH_SHORT).show()
                    is MainState.Apps-> Toast.makeText(this@AppListActivity, "Apps" + it.apps.size, Toast.LENGTH_SHORT).show()
                }
            }
        }*/

    }

    override fun performDependencyInjection(activityComponent: MainActivityComponent) {
        activityComponent.inject(this)
    }
}