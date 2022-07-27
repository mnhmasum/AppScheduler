package com.meldcx.appscheduler.ui.applist

import android.app.Activity
import android.content.Intent
import android.util.Log
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import com.meldcx.appscheduler.R
import com.meldcx.appscheduler.data.AppItem
import com.meldcx.appscheduler.data.AppListIntent
import com.meldcx.appscheduler.data.AppState
import com.meldcx.appscheduler.data.DataState
import com.meldcx.appscheduler.databinding.ActivityAppListBinding
import com.meldcx.appscheduler.dependencyinjection.MainActivityComponent
import com.meldcx.appscheduler.ui.base.BaseActivity
import com.meldcx.appscheduler.utils.Constant.Companion.APP_ID
import com.meldcx.appscheduler.utils.Constant.Companion.APP_NAME
import kotlinx.android.synthetic.main.activity_app_list.*
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

class AppListActivity : BaseActivity<ActivityAppListBinding>() {
    private val TAG = "AppListActivity"

    @Inject
    lateinit var viewModel: AppListViewModel

    @Inject
    lateinit var appListAdapter: AppListAdapter

    override fun getLayoutResource(): Int {
        return R.layout.activity_app_list
    }

    override fun initComponents() {
        binding.apply {
            toolbar.setNavigationOnClickListener { finish() }
            adapter = appListAdapter
        }

        lifecycleScope.launch {
            launch {
                viewModel.appListIntent.send(AppListIntent.FetchApps)
            }
            launch {
                //viewModel.dataState.collect { render(it) }
                viewModel.dataState1.collect {
                    when(it) {
                        is DataState.Success-> it.reduce(false)
                        is DataState.Error -> Unit
                    }
                }
            }
        }

    }

    fun DataState<List<AppItem>>.reduce(isSearchMode: Boolean = false) {
         when (this) {
            is DataState.Success -> Log.d(TAG, "reduce: " + this.data?.get(0)!!.appName!!)
            else -> WalletViewState.Loading
        }
    }

    private fun render(it: AppState) {
        progressBar.isVisible = it is AppState.Loading
        when (it) {
            is AppState.ShowApps -> appListAdapter.setApps(it.data)
            is AppState.App -> showSelectedApp(it.app)
            is AppState.Error -> toast(it.error)
        }
    }

    private fun showSelectedApp(it: AppItem) {
        val data = Intent()
        data.putExtra(APP_NAME, it.appName)
        data.putExtra(APP_ID, it.appLauncher)
        setResult(Activity.RESULT_OK, data)
        finish()
    }

    override fun performDependencyInjection(activityComponent: MainActivityComponent) {
        activityComponent.inject(this)
    }

}