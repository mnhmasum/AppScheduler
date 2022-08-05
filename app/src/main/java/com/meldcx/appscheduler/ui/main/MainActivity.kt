package com.meldcx.appscheduler.ui.main

import com.meldcx.appscheduler.R
import com.meldcx.appscheduler.databinding.ActivityMainBinding
import com.meldcx.appscheduler.dependencyinjection.MainActivityComponent
import com.meldcx.appscheduler.ui.base.BaseActivity
import com.meldcx.appscheduler.ui.create.CreateAlarmActivity
import com.meldcx.appscheduler.ui.currency.CurrencyActivity
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

    override fun initComponents() {
        binding.apply {
            viewModel = mainViewModel
            adapter = mainViewAdapter
            activity = this@MainActivity
        }
    }

    fun openCreateScheduleActivity() {
        //startActivity(CreateAlarmActivity::class.java, false)
        startActivity(CurrencyActivity::class.java, false)
    }
}