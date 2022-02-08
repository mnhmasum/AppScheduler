package com.meldcx.appscheduler.ui.main

import android.view.View
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.meldcx.appscheduler.data.Schedule

@BindingAdapter("setAdapter")
fun setAdapter(recyclerView: RecyclerView, adapter: RecyclerView.Adapter<RecyclerView.ViewHolder>?) {
    adapter?.let {
        recyclerView.adapter = it
    }
}

@BindingAdapter("submitList")
fun submitList(recyclerView: RecyclerView, schedules: List<Schedule>?) {
    val adapter = recyclerView.adapter as AlarmRecyclerViewAdapter?
    adapter?.setAlarms(schedules ?: listOf())
}

@BindingAdapter("android:onLongClick")
fun setOnLongClickListener(view: View, func : () -> Unit) {
    view.setOnLongClickListener {
        func()
        return@setOnLongClickListener true
    }
}
