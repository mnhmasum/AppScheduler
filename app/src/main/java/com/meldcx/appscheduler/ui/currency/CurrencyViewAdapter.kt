package com.meldcx.appscheduler.ui.currency

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.meldcx.appscheduler.R
import com.meldcx.appscheduler.databinding.ItemCurrencyBinding
import com.meldcx.appscheduler.data.ExchangeRate
import java.util.*

class CurrencyViewAdapter(private var currencyConverterViewModel: CurrencyConverterViewModel) :
    RecyclerView.Adapter<CurrencyViewAdapter.CurrencyViewHolder>() {
    private var currencyExchangeRateList: List<ExchangeRate>
    private lateinit var mContext: Context

    inner class CurrencyViewHolder(val recyclerviewAlarmBinding: ItemCurrencyBinding) :
        RecyclerView.ViewHolder(recyclerviewAlarmBinding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CurrencyViewHolder {
        val binding = DataBindingUtil.inflate<ItemCurrencyBinding>(
            LayoutInflater.from(parent.context),
            R.layout.item_currency,
            parent,
            false
        )
        binding.apply {
            adapter = this@CurrencyViewAdapter
            viewModel = currencyConverterViewModel
        }
        this.mContext = parent.context
        return CurrencyViewHolder(binding)
    }

    override fun getItemViewType(position: Int): Int {
        return position
    }

    override fun onBindViewHolder(holder: CurrencyViewHolder, position: Int) {
        holder.recyclerviewAlarmBinding.rate = currencyExchangeRateList[position]
    }

    override fun getItemCount(): Int {
        return currencyExchangeRateList.size
    }

    fun setCurrencyList(data: List<ExchangeRate>?) {
        data?.let { this.currencyExchangeRateList = it }
        notifyDataSetChanged()
    }

    init {
        currencyExchangeRateList = ArrayList()
    }
}