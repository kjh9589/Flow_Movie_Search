package com.kjs.flow.presentation.main.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.kjs.flow.databinding.ViewholderMovieBinding
import com.kjs.model.movie.MovieModel

class MovieAdapter(
    private val listener: MovieListener
): PagingDataAdapter<MovieModel, MovieAdapter.ViewHolder>(diffUtil) {
    interface MovieListener {
        fun moveToDetail(item: MovieModel)
    }
    companion object {
        private val diffUtil = object : DiffUtil.ItemCallback<MovieModel>() {
            override fun areItemsTheSame(oldItem: MovieModel, newItem: MovieModel): Boolean {
                return oldItem.webLink == newItem.webLink
            }

            override fun areContentsTheSame(oldItem: MovieModel, newItem: MovieModel): Boolean {
                return oldItem == newItem
            }
        }
    }

    inner class ViewHolder(private val binding: ViewholderMovieBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(item: MovieModel?) {
            item?.let {
                binding.movieModel = item
                binding.executePendingBindings()

                binding.root.setOnClickListener {
                    listener.moveToDetail(item)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ViewholderMovieBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}