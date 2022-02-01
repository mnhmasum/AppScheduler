package com.meldcx.appscheduler.ui.applist

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import dev.ronnie.imageloaderdagger2.R
import com.meldcx.appscheduler.data.Alarm
import com.meldcx.appscheduler.data.AppItem
import dev.ronnie.imageloaderdagger2.databinding.RecyclerviewAppBinding
import java.util.*

class AppListAdapter(private var appListViewModel: AppListViewModel) : RecyclerView.Adapter<AppListAdapter.AlarmViewHolder>() {
    private var apps: List<AppItem>
    private lateinit var context: Context

    inner class AlarmViewHolder(val recyclerviewAppBinding: RecyclerviewAppBinding) :
        RecyclerView.ViewHolder(recyclerviewAppBinding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AlarmViewHolder {
        val binding = DataBindingUtil.inflate<RecyclerviewAppBinding>(LayoutInflater.from(parent.context), R.layout.recyclerview_app, parent, false)
        //binding.adapter = this
        this.context = parent.context
        return AlarmViewHolder(binding)
    }

    fun onToggle(alarm: Alarm, isChecked: Boolean) {
        if (isChecked) {
            alarm.schedule(context)
            //appListViewModel.update(alarm)
        } else {
            alarm.cancelAlarm(context)
            //appListViewModel.update(alarm)
        }
    }

    override fun getItemViewType(position: Int): Int {
        return position
    }

    override fun onBindViewHolder(holder: AlarmViewHolder, position: Int) {
        //holder.recyclerviewAppBinding.appItem = apps[position]
    }

    override fun getItemCount(): Int {
        return apps.size
    }

    fun setAlarms(alarms: List<AppItem>) {
        this.apps = alarms
        notifyDataSetChanged()
    }

    init {
        apps = ArrayList()
    }
}