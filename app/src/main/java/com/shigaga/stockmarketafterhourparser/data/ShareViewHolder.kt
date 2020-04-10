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