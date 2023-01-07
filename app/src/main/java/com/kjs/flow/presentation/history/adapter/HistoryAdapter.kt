package com.kjs.flow.presentation.history.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.kjs.flow.databinding.ViewholderHistoryBinding
import com.kjs.model.history.HistorySearchKeywordModel

class HistoryAdapter(
    private val listener: HistoryListener
) : ListAdapter<HistorySearchKeywordModel, HistoryAdapter.ViewHolder>(diffUtil) {
    interface HistoryListener {
        fun selectKeyword(keyword: String)
    }

    companion object {
        private val diffUtil = object : DiffUtil.ItemCallback<HistorySearchKeywordModel>() {
            override fun areItemsTheSame(
                oldItem: HistorySearchKeywordModel,
                newItem: HistorySearchKeywordModel
            ): Boolean {
                return oldItem.keyword == newItem.keyword
            }

            override fun areContentsTheSame(
                oldItem: HistorySearchKeywordModel,
                newItem: HistorySearchKeywordModel
            ): Boolean {
                return oldItem == newItem
            }
        }
    }

    init {
        setHasStableIds(true)
    }

    inner class ViewHolder(private val binding: ViewholderHistoryBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: HistorySearchKeywordModel) {
            binding.historyModel = item
            binding.executePendingBindings()

            binding.root.setOnClickListener {
                listener.selectKeyword(item.keyword)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ViewholderHistoryBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(currentList[position])
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }
}