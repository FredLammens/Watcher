package com.example.watcher.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.watcher.databinding.ListItemBinding
import com.example.watcher.models.movies.Result

class MovieAdapter : ListAdapter<Result, RecyclerView.ViewHolder>(ResultDiffCallback()){
    inner class ResultViewHolder(val binding: ListItemBinding): RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder { //niew overviewholder teruggeven
        return ResultViewHolder(ListItemBinding.inflate(LayoutInflater.from(parent.context),parent,false)) //parent nodig voor dingen zoals te weten hoe breed parent is
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val movie = getItem(position)
        (holder as ResultViewHolder).binding.apply{
            item = movie
        }
    }

}

private class ResultDiffCallback: DiffUtil.ItemCallback<Result>() {
    override fun areItemsTheSame(oldItem: Result, newItem: Result): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Result, newItem: Result): Boolean {
        return oldItem == newItem
    }


}
