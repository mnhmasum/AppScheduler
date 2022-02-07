package com.meldcx.appscheduler.ui.applist

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.meldcx.appscheduler.R
import com.meldcx.appscheduler.data.AppItem
import com.meldcx.appscheduler.databinding.RecyclerviewAppBinding
import java.util.*

class AppListAdapter(private var appListViewModel: AppListViewModel) : RecyclerView.Adapter<AppListAdapter.AlarmViewHolder>() {
    private var apps: List<AppItem>
    private lateinit var context: Context

    inner class AlarmViewHolder(val recyclerviewAppBinding: RecyclerviewAppBinding) :
        RecyclerView.ViewHolder(recyclerviewAppBinding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AlarmViewHolder {
        val binding = DataBindingUtil.inflate<RecyclerviewAppBinding>(LayoutInflater.from(parent.context), R.layout.recyclerview_app, parent, false)
        binding.adapter = this
        this.context = parent.context
        binding.viewModel = appListViewModel
        return AlarmViewHolder(binding)
    }

    override fun getItemViewType(position: Int): Int {
        return position
    }

    override fun onBindViewHolder(holder: AlarmViewHolder, position: Int) {
        holder.recyclerviewAppBinding.appItem = apps[position]
    }

    override fun getItemCount(): Int {
        return apps.size
    }

    fun setApps(apps: List<AppItem>) {
        this.apps = apps
        notifyDataSetChanged()
    }

    init {
        apps = ArrayList()
    }
}