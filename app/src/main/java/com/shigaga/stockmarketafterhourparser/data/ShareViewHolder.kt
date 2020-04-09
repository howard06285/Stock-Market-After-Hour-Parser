/*
 * Copyright (C) 2017 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.shigaga.stockmarketafterhourparser.data

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.shigaga.stockmarketafterhourparser.R

/**
 * A simple ViewHolder that can bind a Cheese item. It also accepts null items since the data may
 * not have been fetched before it is bound.
 */
class ShareViewHolder(parent :ViewGroup) : RecyclerView.ViewHolder(
        LayoutInflater.from(parent.context).inflate(R.layout.item, parent, false)) {
    var share : Shares? = null

    private val shareIdTv = itemView.findViewById<TextView>(R.id.shareIdTv)
    private val shareNameTv = itemView.findViewById<TextView>(R.id.shareNameTv)
    private val shareQtyTv = itemView.findViewById<TextView>(R.id.shareQtyTv)
    private val shareStartPriceTv = itemView.findViewById<TextView>(R.id.shareStartPriceTv)
    private val shareEndPriceTv = itemView.findViewById<TextView>(R.id.shareEndPriceTv)

    /**
     * Items might be null if they are not paged in yet. PagedListAdapter will re-bind the
     * ViewHolder when Item is loaded.
     */
    fun bindTo(share : Shares?) {
        this.share = share
        shareIdTv.text = share?.id
        shareNameTv.text = share?.name
        shareQtyTv.text = share?.qty
        shareStartPriceTv.text = share?.startPrice
        shareEndPriceTv.text = share?.endPrice
    }
}