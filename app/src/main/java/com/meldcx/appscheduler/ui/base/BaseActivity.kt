package com.meldcx.appscheduler.ui.base

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.provider.Settings
import android.widget.Toast
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import com.meldcx.appscheduler.application.MainApplication
import com.meldcx.appscheduler.dependencyinjection.ActivityModule
import com.meldcx.appscheduler.dependencyinjection.DaggerMainActivityComponent
import com.meldcx.appscheduler.dependencyinjection.MainActivityComponent

abstract class BaseActivity<T : ViewDataBinding> : AppCompatActivity() {
    lateinit var binding: T
    abstract fun getLayoutResource(): Int
    abstract fun initComponents()
    abstract fun performDependencyInjection(activityComponent: MainActivityComponent)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, getLayoutResource())
        binding.lifecycleOwner = this
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

    fun toast(message: String) {
        Toast.makeText(this, "Toast: $message", Toast.LENGTH_SHORT).show()
    }

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onResume() {
        launchOverlayAppPermission()
        super.onResume()
    }

    @RequiresApi(Build.VERSION_CODES.M)
    private fun launchOverlayAppPermission() {
        if (!Settings.canDrawOverlays(this)) {
            val intent = Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION, Uri.parse("package:$packageName"))
            startForResult.launch(intent)
        }
    }

    private val startForResult = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result: ActivityResult ->
            if (result.resultCode == Activity.RESULT_OK) {
                toast("Permission Granted")
            }
        }
}