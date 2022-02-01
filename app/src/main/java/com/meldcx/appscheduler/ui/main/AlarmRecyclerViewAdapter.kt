package com.meldcx.appscheduler.ui.main

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import dev.ronnie.imageloaderdagger2.R
import com.meldcx.appscheduler.data.Alarm
import dev.ronnie.imageloaderdagger2.databinding.RecyclerviewAlarmBinding
import java.util.*

class AlarmRecyclerViewAdapter(private var mainViewModel: MainViewModel) : RecyclerView.Adapter<AlarmRecyclerViewAdapter.AlarmViewHolder>() {
    private var alarms: List<Alarm>
    private lateinit var context: Context

    inner class AlarmViewHolder(val recyclerviewAlarmBinding: RecyclerviewAlarmBinding) :
        RecyclerView.ViewHolder(recyclerviewAlarmBinding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AlarmViewHolder {
        val binding = DataBindingUtil.inflate<RecyclerviewAlarmBinding>(LayoutInflater.from(parent.context), R.layout.recyclerview_alarm, parent, false)
        binding.adapter = this
        this.context = parent.context
        return AlarmViewHolder(binding)
    }

    fun onToggle(alarm: Alarm, isChecked: Boolean) {
        if (isChecked) {
            alarm.schedule(context)
            mainViewModel.update(alarm)
        } else {
            alarm.cancelAlarm(context)
            mainViewModel.update(alarm)
        }
    }

    override fun getItemViewType(position: Int): Int {
        return position
    }

    override fun onBindViewHolder(holder: AlarmViewHolder, position: Int) {
        holder.recyclerviewAlarmBinding.alarm = alarms[position]
    }

    override fun getItemCount(): Int {
        return alarms.size
    }

    fun setAlarms(alarms: List<Alarm>) {
        this.alarms = alarms
        notifyDataSetChanged()
    }

    init {
        alarms = ArrayList()
    }
}