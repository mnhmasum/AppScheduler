package com.meldcx.test.utils

import android.content.Context
import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.TimePicker
import android.widget.Toast
import androidx.viewbinding.ViewBinding
import com.meldcx.appscheduler.data.Alarm
import com.meldcx.appscheduler.utils.TimePickerUtil
import dev.ronnie.imageloaderdagger2.databinding.ActivityCreatealarmBinding
import kotlinx.android.synthetic.main.activity_createalarm.*
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

fun Context.getTaskHour(resId: TimePicker):Int = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
        resId.getHour()
    } else {
        resId.getCurrentHour()
    }

fun Context.buildTask(t:TimePicker, b: ActivityCreatealarmBinding, appId:String): Alarm {
    return Alarm(
        Random().nextInt(Int.MAX_VALUE),
        getTaskHour(b.timePicker),
        TimePickerUtil.getTimePickerMinute(b.timePicker),
        b.textAppPackageName.text.toString(),
        appId,
        System.currentTimeMillis(),
        true,
        b.checkRecurring.isChecked,
        b.checkMon.isChecked,
        b.checkTue.isChecked,
        b.checkWed.isChecked,
        b.checkThu.isChecked,
        b.checkFri.isChecked,
        b.checkSat.isChecked,
        b.checkSun.isChecked
    )
}



