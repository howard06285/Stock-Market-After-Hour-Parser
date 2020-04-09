package com.shigaga.stockmarketafterhourparser

import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import com.shigaga.stockmarketafterhourparser.data.ShareViewHolder
import com.shigaga.stockmarketafterhourparser.data.Shares


class DataAdapter: PagedListAdapter<Shares, ShareViewHolder>(diffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShareViewHolder = ShareViewHolder(parent)

    override fun onBindViewHolder(holder: ShareViewHolder, position: Int) {
        holder.bindTo(getItem(position))
    }

    companion object {
        /**
         * This diff callback informs the PagedListAdapter how to compute list differences when new
         * PagedLists arrive.
         * <p>
         * When you add a Cheese with the 'Add' button, the PagedListAdapter uses diffCallback to
         * detect there's only a single item difference from before, so it only needs to animate and
         * rebind a single view.
         *
         * @see android.support.v7.util.DiffUtil
         */
        private val diffCallback = object : DiffUtil.ItemCallback<Shares>() {
            override fun areItemsTheSame(oldItem: Shares, newItem: Shares): Boolean =
                oldItem.id == newItem.id

            /**
             * Note that in kotlin, == checking on data classes compares all contents, but in Java,
             * typically you'll implement Object#equals, and use it to compare object contents.
             */
            override fun areContentsTheSame(oldItem: Shares, newItem: Shares): Boolean =
                oldItem == newItem
        }
    }
}