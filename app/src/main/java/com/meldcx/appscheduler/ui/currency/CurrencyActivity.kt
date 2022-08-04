package com.meldcx.appscheduler.ui.currency

import android.os.Build
import androidx.annotation.RequiresApi
import com.meldcx.appscheduler.dependencyinjection.MainActivityComponent
import com.meldcx.appscheduler.ui.base.BaseActivity
import com.meldcx.appscheduler.ui.create.CreateAlarmActivity
import com.meldcx.appscheduler.R
import com.meldcx.appscheduler.databinding.ActivityMainBinding
import javax.inject.Inject


class CurrencyActivity : BaseActivity<ActivityMainBinding>() {
    @Inject
    lateinit var currencyViewModel: CurrencyViewModel

    @Inject
    lateinit var currencyViewAdapter: CurrencyViewAdapter

    override fun performDependencyInjection(activityComponent: MainActivityComponent) {
        activityComponent.inject(this)
    }

    override fun getLayoutResource(): Int {
        return R.layout.activity_main
    }

    @RequiresApi(Build.VERSION_CODES.M)
    override fun initComponents() {
        /*binding.apply {
            viewModel = currencyViewModel
            adapter = currencyViewAdapter
            activity = this@CurrencyActivity
        }*/
    }
}