package com.meldcx.appscheduler.ui.main

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.meldcx.appscheduler.R
import com.meldcx.appscheduler.data.Schedule
import com.meldcx.appscheduler.databinding.RecyclerviewAlarmBinding
import java.util.*

class MainViewAdapter(private var mainViewModel: MainViewModel) : RecyclerView.Adapter<MainViewAdapter.AlarmViewHolder>() {
    private var mScheduleList: List<Schedule>
    private lateinit var mContext: Context

    inner class AlarmViewHolder(val recyclerviewAlarmBinding: RecyclerviewAlarmBinding) :
        RecyclerView.ViewHolder(recyclerviewAlarmBinding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AlarmViewHolder {
        val binding = DataBindingUtil.inflate<RecyclerviewAlarmBinding>(LayoutInflater.from(parent.context), R.layout.recyclerview_alarm, parent, false)
        binding.apply {
            adapter = this@MainViewAdapter
            viewModel = mainViewModel
        }
        this.mContext = parent.context
        return AlarmViewHolder(binding)
    }

    fun onToggle(schedule: Schedule, isChecked: Boolean) {
        if (isChecked) {
            schedule.schedule(mContext)
            mainViewModel.update(schedule)
        } else {
            schedule.cancelAlarm(mContext)
            mainViewModel.update(schedule)
        }
    }

    fun delete(schedule: Schedule) {
        mainViewModel.delete(schedule)
        schedule.cancelAlarm(mContext)
    }

    override fun getItemViewType(position: Int): Int {
        return position
    }

    override fun onBindViewHolder(holder: AlarmViewHolder, position: Int) {
        holder.recyclerviewAlarmBinding.alarm = mScheduleList[position]
    }

    override fun getItemCount(): Int {
        return mScheduleList.size
    }

    fun setAlarms(schedules: List<Schedule>) {
        this.mScheduleList = schedules
        notifyDataSetChanged()
    }

    init {
        mScheduleList = ArrayList()
    }
}