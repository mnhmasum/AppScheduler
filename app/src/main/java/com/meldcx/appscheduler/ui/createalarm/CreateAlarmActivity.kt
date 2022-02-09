package com.meldcx.appscheduler.ui.createalarm

import android.app.Activity
import android.content.Intent
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import com.meldcx.appscheduler.data.Schedule
import com.meldcx.appscheduler.di.MainActivityComponent
import com.meldcx.appscheduler.ui.applist.AppListActivity
import com.meldcx.appscheduler.ui.base.BaseActivity
import com.meldcx.appscheduler.utils.Constant.Companion.APP_ID
import com.meldcx.appscheduler.utils.Constant.Companion.APP_NAME
import com.meldcx.test.utils.buildTask
import com.meldcx.appscheduler.R
import com.meldcx.appscheduler.databinding.ActivityCreatealarmBinding
import kotlinx.android.synthetic.main.activity_createalarm.*
import java.util.*
import javax.inject.Inject

class CreateAlarmActivity : BaseActivity<ActivityCreatealarmBinding>() {
    @Inject
    lateinit var createTaskViewModel: CreateScheduleViewModel
    private var mAppId: String = String()

    override fun performDependencyInjection(activityComponent: MainActivityComponent) {
        activityComponent.inject(this)
    }

    override fun getLayoutResource(): Int {
        return R.layout.activity_createalarm
    }

    override fun initComponents() {
        binding.apply {
            toolbar.setNavigationOnClickListener { finish() }
            viewModel = createTaskViewModel
            activity = this@CreateAlarmActivity
        }
    }

    fun onClickCreateTask() {
        val task = buildTask(binding, mAppId)
        if (createTaskViewModel.isTaskValid(task)) {
            createTaskViewModel.insert(task)
            task.scheduleAndClose()
        }
    }

    private fun Schedule.scheduleAndClose() {
        this.schedule(this@CreateAlarmActivity)
        finish()
    }

    fun openAppList() {
        startForResult.launch(Intent(this, AppListActivity::class.java))
    }

    private val startForResult =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result: ActivityResult ->
            if (result.resultCode == Activity.RESULT_OK) {
                binding.textAppPackageName.text = result.data?.getStringExtra(APP_NAME)
                mAppId = result.data?.getStringExtra(APP_ID)!!
            }
        }

}