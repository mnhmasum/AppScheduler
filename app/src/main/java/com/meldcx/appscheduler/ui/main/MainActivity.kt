package com.meldcx.appscheduler.ui.main

import android.os.Build
import androidx.annotation.RequiresApi
import com.meldcx.appscheduler.dependencyinjection.MainActivityComponent
import com.meldcx.appscheduler.ui.base.BaseActivity
import com.meldcx.appscheduler.ui.createalarm.CreateAlarmActivity
import com.meldcx.appscheduler.R
import com.meldcx.appscheduler.databinding.ActivityMainBinding
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject


class MainActivity : BaseActivity<ActivityMainBinding>() {
    @Inject
    lateinit var mainViewModel: MainViewModel

    @Inject
    lateinit var mainViewAdapter: MainViewAdapter

    override fun performDependencyInjection(activityComponent: MainActivityComponent) {
        activityComponent.inject(this)
    }

    override fun getLayoutResource(): Int {
        return R.layout.activity_main
    }

    @RequiresApi(Build.VERSION_CODES.M)
    override fun initComponents() {
        binding.apply {
            viewModel = mainViewModel
            adapter = mainViewAdapter
            activity = this@MainActivity
        }
    }

    fun openAlarmActivity() {
        startActivity(CreateAlarmActivity::class.java, false)
    }
}