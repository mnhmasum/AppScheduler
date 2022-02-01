package com.meldcx.appscheduler.ui.base

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import com.meldcx.appscheduler.application.MainApplication
import com.meldcx.appscheduler.di.DaggerMainActivityComponent
import com.meldcx.appscheduler.di.MainActivityComponent
import com.meldcx.appscheduler.di.ActivityModule

abstract class BaseActivity<T : ViewDataBinding> : AppCompatActivity() {
    lateinit var binding: T
    abstract fun getLayoutResource(): Int
    abstract fun initComponents()
    abstract fun performDependencyInjection(activityComponent: MainActivityComponent)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, getLayoutResource())
        setContentView(binding.root)
        performDependencyInjection(getActivityComponent())
        initComponents()
    }

    private fun getActivityComponent(): MainActivityComponent {
        return DaggerMainActivityComponent.builder()
            .applicationComponent((application as MainApplication).applicationComponent)
            .activityModule(ActivityModule(this))
            .build()
    }

    open fun startActivity(cls: Class<*>?, finishSelf: Boolean) {
        val intent = Intent(this, cls)
        startActivity(intent)
        if (finishSelf) {
            finish()
        }
    }

    override fun onResume() {
        binding.lifecycleOwner = this
        super.onResume()
    }
}