package com.lezhin.test.ui.main

import android.arch.paging.PagedListAdapter
import android.support.v7.util.DiffUtil
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.lezhin.test.R
import com.lezhin.test.api.data.Document
import com.lezhin.test.databinding.MainItemBinding
import com.lezhin.test.ui.detail.DetailActivity
import com.lezhin.test.utils.Global
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.toast

class MainAdapter: PagedListAdapter<Document, MainAdapter.MainViewHolder>(diffCallback) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = MainItemBinding.inflate(inflater)
        return MainViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        getItem(position)?.let {
            holder.bind(it)
        }
    }

    class MainViewHolder(private val binding: MainItemBinding)
        : RecyclerView.ViewHolder(binding.root), MainAdapterListener {
        fun bind(document: Document) {
            binding.document = document
            binding.listener = this
            binding.executePendingBindings()
        }

        override fun onClickListener(view: View) {
            if (binding.document == null) {
                view.context.toast(R.string.error_api)
            }
            view.context.startActivity<DetailActivity>(
                Global.EXTRA_DETAIL_PHOTO to binding.document?.imageUrl,
                Global.EXTRA_DETAIL_DOC_URL to binding.document?.docUrl
            )
        }
    }

    companion object {
        private val diffCallback = object : DiffUtil.ItemCallback<Document>() {
            override fun areItemsTheSame(oldItem: Document?, newItem: Document?): Boolean =
                oldItem?.imageUrl == newItem?.imageUrl

            override fun areContentsTheSame(oldItem: Document?, newItem: Document?): Boolean =
                oldItem?.imageUrl == newItem?.imageUrl &&
                oldItem?.thumbnailUrl == newItem?.thumbnailUrl &&
                oldItem?.docUrl == newItem?.docUrl

        }
    }
}
