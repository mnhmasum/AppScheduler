package com.meldcx.appscheduler.ui.main

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.meldcx.appscheduler.R
import com.meldcx.appscheduler.data.Alarm
import com.meldcx.appscheduler.databinding.RecyclerviewAlarmBinding
import java.util.*

class AlarmRecyclerViewAdapter(private var mainViewModel: MainViewModel) : RecyclerView.Adapter<AlarmRecyclerViewAdapter.AlarmViewHolder>() {
    private var mAlarmList: List<Alarm>
    private lateinit var mContext: Context

    inner class AlarmViewHolder(val recyclerviewAlarmBinding: RecyclerviewAlarmBinding) :
        RecyclerView.ViewHolder(recyclerviewAlarmBinding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AlarmViewHolder {
        val binding = DataBindingUtil.inflate<RecyclerviewAlarmBinding>(LayoutInflater.from(parent.context), R.layout.recyclerview_alarm, parent, false)
        binding.apply {
            adapter = this@AlarmRecyclerViewAdapter
            viewModel = mainViewModel
        }
        this.mContext = parent.context
        return AlarmViewHolder(binding)
    }

    fun onToggle(alarm: Alarm, isChecked: Boolean) {
        if (isChecked) {
            alarm.schedule(mContext)
            mainViewModel.update(alarm)
        } else {
            alarm.cancelAlarm(mContext)
            mainViewModel.update(alarm)
        }
    }

    fun delete(alarm: Alarm) {
        mainViewModel.delete(alarm)
        alarm.cancelAlarm(mContext)
    }

    override fun getItemViewType(position: Int): Int {
        return position
    }

    override fun onBindViewHolder(holder: AlarmViewHolder, position: Int) {
        holder.recyclerviewAlarmBinding.alarm = mAlarmList[position]
    }

    override fun getItemCount(): Int {
        return mAlarmList.size
    }

    fun setAlarms(alarms: List<Alarm>) {
        this.mAlarmList = alarms
        notifyDataSetChanged()
    }

    init {
        mAlarmList = ArrayList()
    }
}