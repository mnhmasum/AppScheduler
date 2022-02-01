package com.meldcx.appscheduler.ui.createalarm

import com.meldcx.appscheduler.data.Alarm
import com.meldcx.appscheduler.di.MainActivityComponent
import com.meldcx.appscheduler.ui.applist.AppListActivity
import com.meldcx.appscheduler.ui.base.BaseActivity
import com.meldcx.appscheduler.utils.TimePickerUtil
import dev.ronnie.imageloaderdagger2.R
import dev.ronnie.imageloaderdagger2.databinding.ActivityCreatealarmBinding
import kotlinx.android.synthetic.main.activity_createalarm.*
import java.util.*
import javax.inject.Inject

class CreateAlarmActivity : BaseActivity<ActivityCreatealarmBinding>() {
    @Inject
    lateinit var createAlarmViewModel: CreateAlarmViewModel

    override fun performDependencyInjection(activityComponent: MainActivityComponent) {
        activityComponent.inject(this)
    }

    override fun getLayoutResource(): Int {
        return R.layout.activity_createalarm
    }

    override fun initComponents() {
        binding.apply {
            viewModel = createAlarmViewModel
            lifecycleOwner = this@CreateAlarmActivity
            activity = this@CreateAlarmActivity
        }
    }

    fun createAlarm() {
        scheduleAlarm()
    }

    fun openAppList(){
        startActivity(AppListActivity::class.java, false)
    }

    private fun scheduleAlarm() {
        val alarmId = Random().nextInt(Int.MAX_VALUE)
        val alarm = Alarm(
            alarmId,
            TimePickerUtil.getTimePickerHour(fragment_createalarm_timePicker),
            TimePickerUtil.getTimePickerMinute(fragment_createalarm_timePicker),
            text_app_package_name.text.toString(),
            System.currentTimeMillis(),
            true,
            check_recurring.isChecked,
            check_mon.isChecked,
            check_tue.isChecked,
            check_wed.isChecked,
            check_thu.isChecked,
            check_fri.isChecked,
            check_sat.isChecked,
            check_sun.isChecked
        )
        createAlarmViewModel.insert(alarm)
        alarm.schedule(this)
        finish()
    }
}