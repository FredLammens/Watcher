package com.example.watcher.utils

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView

@BindingAdapter(value = ["setAdapter"]) //voor in overviewfragment adapter mee te kunnen geven.
fun RecyclerView.bindRecyclerViewAdapter(adapter: RecyclerView.Adapter<*>){ // enkel recyclerviewadapters
    this.run {
        this.adapter = adapter
    }
}