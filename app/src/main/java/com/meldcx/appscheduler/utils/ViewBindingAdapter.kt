package com.meldcx.appscheduler.ui.main

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.meldcx.appscheduler.data.Alarm
import com.meldcx.appscheduler.data.AppItem
import com.meldcx.appscheduler.ui.applist.AppListAdapter

@BindingAdapter("setAdapter")
fun setAdapter(recyclerView: RecyclerView, adapter: RecyclerView.Adapter<RecyclerView.ViewHolder>?) {
    adapter?.let {
        recyclerView.adapter = it
    }
}

@Suppress("UNCHECKED_CAST")
@BindingAdapter("submitList")
fun submitList(recyclerView: RecyclerView, alarms: List<Alarm>?) {
    val adapter = recyclerView.adapter as AlarmRecyclerViewAdapter?
    adapter?.setAlarms(alarms ?: listOf())
}

@BindingAdapter("submitAppList")
fun submitAppList(recyclerView: RecyclerView, appItems: List<AppItem>?) {
    val adapter = recyclerView.adapter as AppListAdapter?
    adapter?.setAlarms(appItems ?: listOf())
}

/*
@Suppress("UNCHECKED_CAST")
@BindingAdapter("submitList")
fun submitList(recyclerView: RecyclerView, list: List<ListAdapterItem>?) {
    val adapter = recyclerView.adapter as BaseAdapter<ViewDataBinding, ListAdapterItem>?
    adapter?.updateData(list ?: listOf())
}

@BindingAdapter("manageState")
fun manageState(progressBar: ProgressBar, state: Boolean) {
    progressBar.visibility = if (state) View.VISIBLE else View.GONE
}

@BindingAdapter("setImage")
fun setImage(imageView: ShapeableImageView, image: Int) {
    Glide.with(imageView.context)
        .load(image)
        .into(imageView)
}

@BindingAdapter("setFavouriteCondition")
fun setFavouriteCondition(imageView: ShapeableImageView, isFavourite: Boolean) {
    if (isFavourite) {
        imageView.setImageResource(R.drawable.ic_favorite)
    } else {
        imageView.setImageResource(R.drawable.ic_favorite_border)
    }

}*/
