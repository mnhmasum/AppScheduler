package com.meldcx.appscheduler.ui.currency

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import com.meldcx.appscheduler.R
import com.meldcx.appscheduler.data.Rate
import kotlinx.android.synthetic.main.item_rate.view.*

class CurrencySpinnerAdapter(ctx: Context, moods: List<Rate>) : ArrayAdapter<Rate>(ctx, 0, moods) {

    override fun getView(position: Int, recycledView: View?, parent: ViewGroup): View {
        return this.createView(position, recycledView, parent)
    }

    override fun getDropDownView(position: Int, recycledView: View?, parent: ViewGroup): View {
        return this.createView(position, recycledView, parent)
    }

    private fun createView(position: Int, recycledView: View?, parent: ViewGroup): View {
        val mood = getItem(position)
        val view = recycledView ?: LayoutInflater.from(context).inflate(R.layout.item_rate, parent, false)
        view.textViewTitle.text = mood?.currencyName
        return view
    }
}