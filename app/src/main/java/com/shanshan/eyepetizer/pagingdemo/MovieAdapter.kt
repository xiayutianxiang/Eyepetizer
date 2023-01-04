package com.shanshan.eyepetizer.pagingdemo

import android.annotation.SuppressLint
import android.content.ClipData
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.DifferCallback
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import com.pax.data.Movie
import com.shanshan.eyepetizer.databinding.ItemMovieBinding

class MovieAdapter(private val context: Context) :
    PagingDataAdapter<Movie, MovieAdapter.BindingViewHolder>(object :
        DiffUtil.ItemCallback<Movie>() {
        override fun areItemsTheSame(oldItem: Movie, newItem: Movie): Boolean {
            return oldItem.id == newItem.id
        }

        @SuppressLint("DiffUtilEquals")
        override fun areContentsTheSame(oldItem: Movie, newItem: Movie): Boolean {
            return oldItem == newItem
        }

    }) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BindingViewHolder {
        val binding = ItemMovieBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return BindingViewHolder(binding)
    }

    override fun onBindViewHolder(holder: BindingViewHolder, position: Int) {
        val movie = getItem(position)
        val binding = holder.binding as ItemMovieBinding
        movie?.let {
            // binding.imageView = it.cover
            binding.textViewTitle.text = it.title
            binding.textViewRate.text = it.rate
        }
    }

    inner class BindingViewHolder(val binding: ViewBinding) : RecyclerView.ViewHolder(binding.root)
}