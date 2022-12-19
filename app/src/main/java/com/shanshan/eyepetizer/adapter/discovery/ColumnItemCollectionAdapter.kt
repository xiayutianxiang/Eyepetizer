package com.shanshan.eyepetizer.adapter.discovery

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.shanshan.eyepetizer.R
import com.shanshan.eyepetizer.data.ItemX
import com.shanshan.eyepetizer.utils.LogUtils

class ColumnItemCollectionAdapter(val itemList: List<ItemX>) :
    RecyclerView.Adapter<ColumnItemCollectionAdapter.ColumnItemCollectionHolder>() {

    companion object{
        const val TAG = "ColumnItemCollectionAdapter"
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ColumnItemCollectionHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_column_card_collection, parent, false)
        return ColumnItemCollectionHolder(view)
    }

    override fun onBindViewHolder(holder: ColumnItemCollectionHolder, position: Int) {
        LogUtils.d(TAG,itemList[position].data.image)
        Glide.with(holder.itemView.context).load(itemList[position].data.image).into(
            holder.squareCardImg
        )
        holder.squareCardTitle.text = itemList[position].data.title
        holder.squareCardDescription.text = itemList[position].data.description
    }

    override fun getItemCount(): Int {
        return itemList.size
    }

    inner class ColumnItemCollectionHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val squareCardImg: ImageView = itemView.findViewById(R.id.squareCard_img)
        val squareCardTitle: TextView = itemView.findViewById(R.id.squareCard_title)
        val squareCardDescription: TextView = itemView.findViewById(R.id.squareCard_description)
    }
}