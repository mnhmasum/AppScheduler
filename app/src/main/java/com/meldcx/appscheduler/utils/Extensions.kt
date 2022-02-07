package com.meldcx.test.utils

import android.content.Context
import android.os.Build
import android.widget.TimePicker
import com.meldcx.appscheduler.data.Alarm
import dev.ronnie.imageloaderdagger2.databinding.ActivityCreatealarmBinding
import java.util.*

/*
val Context.inputMethodManager: InputMethodManager
    get() = this.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager

val View.inputMethodManager: InputMethodManager
    get() = this.context.inputMethodManager

val View.dismissKeyboard: Boolean
    get() = this.inputMethodManager.hideSoftInputFromWindow(this.windowToken, 0)

val Context.layoutInflater: LayoutInflater
    get() = LayoutInflater.from(this)

val View.layoutInflater: LayoutInflater
    get() = this.context.layoutInflater

fun Context.toast(text: CharSequence, duration: Int = Toast.LENGTH_SHORT) =
    Toast.makeText(this, text, duration).show()
*/

fun getTimePickerHour(resId: TimePicker):Int = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
        resId.getHour()
    } else {
        resId.getCurrentHour()
    }

fun getTimePickerMinute(resId: TimePicker):Int = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
    resId.minute
} else {
    resId.currentMinute
}

fun Context.buildTask(binding: ActivityCreatealarmBinding, appId:String): Alarm {
    return Alarm(
        Random().nextInt(Int.MAX_VALUE),
        getTimePickerHour(binding.timePicker),
        getTimePickerMinute(binding.timePicker),
        binding.textAppPackageName.text.toString(),
        appId,
        System.currentTimeMillis(),
        true,
        binding.checkRecurring.isChecked,
        binding.checkMon.isChecked,
        binding.checkTue.isChecked,
        binding.checkWed.isChecked,
        binding.checkThu.isChecked,
        binding.checkFri.isChecked,
        binding.checkSat.isChecked,
        binding.checkSun.isChecked
    )
}



