package com.meldcx.appscheduler.ui.currency

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.meldcx.appscheduler.R
import com.meldcx.appscheduler.data.Schedule
import com.meldcx.appscheduler.databinding.ItemCurrencyBinding
import com.meldcx.appscheduler.data.CurrencyData
import com.meldcx.appscheduler.data.Rate
import java.util.*

class CurrencyViewAdapter(private var currencyViewModel: CurrencyViewModel) : RecyclerView.Adapter<CurrencyViewAdapter.CurrencyViewHolder>() {
    private var currencyRateList: List<Rate>
    private lateinit var mContext: Context

    inner class CurrencyViewHolder(val recyclerviewAlarmBinding: ItemCurrencyBinding) :
        RecyclerView.ViewHolder(recyclerviewAlarmBinding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CurrencyViewHolder {
        val binding = DataBindingUtil.inflate<ItemCurrencyBinding>(LayoutInflater.from(parent.context), R.layout.item_currency, parent, false)
        binding.apply {
            adapter = this@CurrencyViewAdapter
            viewModel = currencyViewModel
        }
        this.mContext = parent.context
        return CurrencyViewHolder(binding)
    }

    fun onToggle(schedule: Schedule, isChecked: Boolean) {
        if (isChecked) {
            schedule.schedule(mContext)
            //currencyViewModel.update(schedule)
        } else {
            schedule.cancelAlarm(mContext)
            //currencyViewModel.update(schedule)
        }
    }

    fun delete(schedule: Schedule) {
        //currencyViewModel.delete(schedule)
        schedule.cancelAlarm(mContext)
    }

    override fun getItemViewType(position: Int): Int {
        return position
    }

    override fun onBindViewHolder(holder: CurrencyViewHolder, position: Int) {
        holder.recyclerviewAlarmBinding.rate = currencyRateList[position]
    }

    override fun getItemCount(): Int {
        return currencyRateList.size
    }

    fun setCurrencyList(data: CurrencyData?) {
        val rates = ArrayList<Rate>()
       /* data?.let {
            rates.add(
                Rate(
                    "aed",
                    it
                )
            )
            rates.add(
                Rate(
                    "afn",
                    it.getRates().afn
                )
            )
            rates.add(
                Rate(
                    "all",
                    it.getRates().all
                )
            )
            rates.add(
                Rate(
                    "amd",
                    it.getRates().amd
                )
            )*/
        //}
        this.currencyRateList = data?.rateList!!
        notifyDataSetChanged()
    }

    init {
        currencyRateList = ArrayList()
    }
}