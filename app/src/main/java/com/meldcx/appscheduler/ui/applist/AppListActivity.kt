package com.meldcx.appscheduler.ui.applist

import android.app.Activity
import android.content.Intent
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import com.meldcx.appscheduler.data.AppItem
import com.meldcx.appscheduler.data.AppListIntent
import com.meldcx.appscheduler.data.AppState
import com.meldcx.appscheduler.di.MainActivityComponent
import com.meldcx.appscheduler.ui.base.BaseActivity
import dev.ronnie.imageloaderdagger2.R
import dev.ronnie.imageloaderdagger2.databinding.ActivityAppListBinding
import kotlinx.android.synthetic.main.activity_app_list.*
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

class AppListActivity : BaseActivity<ActivityAppListBinding>() {
    @Inject
    lateinit var viewModel: AppListViewModel

    @Inject
    lateinit var appListAdapter: AppListAdapter

    override fun getLayoutResource(): Int {
        return R.layout.activity_app_list
    }

    override fun initComponents() {
        binding.apply {
            adapter = appListAdapter
        }

        lifecycleScope.launch {
            launch {
                viewModel.appListIntent.send(AppListIntent.FetchApps)
            }
            launch {
                viewModel.dataState.collect { render(it) }
            }
        }

    }

    private fun render(it: AppState) {
        progressBar.isVisible = it is AppState.Loading
        when (it) {
            is AppState.Apps -> appListAdapter.setApps(it.data)
            is AppState.App -> showSelectedApp(it.app)
            is AppState.Error -> toast(it.error)
        }
    }

    private fun showSelectedApp(it: AppItem) {
        val data = Intent()
        data.putExtra("app_name", it.appLauncher)
        setResult(Activity.RESULT_OK, data)
        finish()
    }

    override fun performDependencyInjection(activityComponent: MainActivityComponent) {
        activityComponent.inject(this)
    }

}