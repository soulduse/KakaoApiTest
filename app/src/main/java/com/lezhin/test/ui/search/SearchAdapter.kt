package com.lezhin.test.ui.search

import android.arch.paging.PagedListAdapter
import android.support.v7.util.DiffUtil
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.lezhin.test.databinding.SearchItemBinding
import org.jetbrains.anko.toast


class SearchAdapter(private val keyword: String,
                    private val listener: SearchNavigation) {
//    PagedListAdapter<Node, SearchAdapter.SearchViewHolder>(diffCallback) {

    /*
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = SearchItemBinding.inflate(inflater)
        return SearchViewHolder(binding)
    }

    override fun onBindViewHolder(holder: SearchViewHolder, position: Int) {
        getItem(position)?.let {
            holder.bind(it)
        }
    }

    class SearchViewHolder(private val binding: SearchItemBinding)
        : RecyclerView.ViewHolder(binding.root) {
        fun bind(node: Node) {
            binding.node = node
            binding.listener = listener
            binding.executePendingBindings()
        }
    }

    companion object {
        private val diffCallback = object : DiffUtil.ItemCallback<Node>() {
            override fun areItemsTheSame(oldItem: Node?, newItem: Node?): Boolean =
                    oldItem?.id == newItem?.id

            override fun areContentsTheSame(oldItem: Node?, newItem: Node?): Boolean =
                    oldItem?.id == newItem?.id
        }
    }
*/
}
