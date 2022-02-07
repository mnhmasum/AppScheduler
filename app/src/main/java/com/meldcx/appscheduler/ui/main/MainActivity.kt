package com.meldcx.appscheduler.ui.main

import android.os.Build
import androidx.annotation.RequiresApi
import com.meldcx.appscheduler.di.MainActivityComponent
import com.meldcx.appscheduler.ui.base.BaseActivity
import com.meldcx.appscheduler.ui.createalarm.CreateAlarmActivity
import dev.ronnie.imageloaderdagger2.R
import dev.ronnie.imageloaderdagger2.databinding.ActivityMainBinding
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject


class MainActivity : BaseActivity<ActivityMainBinding>() {
    @Inject
    lateinit var mainViewModel: MainViewModel

    @Inject
    lateinit var alarmRecyclerViewAdapter: AlarmRecyclerViewAdapter

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
            adapter = alarmRecyclerViewAdapter
            activity = this@MainActivity
        }
    }

    fun openAlarmActivity() {
        startActivity(CreateAlarmActivity::class.java, false)
    }
}