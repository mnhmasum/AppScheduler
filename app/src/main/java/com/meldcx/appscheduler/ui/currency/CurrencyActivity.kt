package com.meldcx.appscheduler.ui.currency

import android.os.Build
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import com.meldcx.appscheduler.dependencyinjection.MainActivityComponent
import com.meldcx.appscheduler.ui.base.BaseActivity
import com.meldcx.appscheduler.R
import com.meldcx.appscheduler.data.AppListIntent
import com.meldcx.appscheduler.data.AppState
import com.meldcx.appscheduler.databinding.ActivityCurrencyConvertBinding
import kotlinx.android.synthetic.main.activity_app_list.*
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject


class CurrencyActivity : BaseActivity<ActivityCurrencyConvertBinding>() {
    @Inject
    lateinit var currencyViewModel: CurrencyViewModel

    @Inject
    lateinit var currencyViewAdapter: CurrencyViewAdapter

    override fun performDependencyInjection(activityComponent: MainActivityComponent) {
        activityComponent.inject(this)
    }

    override fun getLayoutResource(): Int {
        return R.layout.activity_currency_convert
    }

    @RequiresApi(Build.VERSION_CODES.M)
    override fun initComponents() {
        binding.apply {
            viewModel = currencyViewModel
            adapter = currencyViewAdapter
            activity = this@CurrencyActivity
        }

        lifecycleScope.launch {
            launch {
                currencyViewModel.appListIntent.send(AppListIntent.FetchApps)
            }
            launch {
                currencyViewModel.dataState.collect { render(it) }
            }
        }
    }

    private fun render(it: AppState) {
        progressBar.isVisible = it is AppState.Loading
        when (it) {
            is AppState.Success -> {toast("Base Currency ${it.list?.body()?.getBase()}")
            //currencyViewAdapter.setCurrencyList(it.list?.body()!)
            }
            is AppState.Error -> toast(it.error)
        }
    }
}