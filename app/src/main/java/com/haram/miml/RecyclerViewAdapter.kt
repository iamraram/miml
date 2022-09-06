package com.haram.miml

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class RecyclerViewAdapter(private val recyclerViewItems: ArrayList<recyclerViewItem>): RecyclerView.Adapter<RecyclerViewAdapter.RecyclerViewViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerViewViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.recyclerview, parent, false)
        return RecyclerViewViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: RecyclerViewViewHolder, position: Int) {
        holder.bind(recyclerViewItems[position], position)
    }

    override fun getItemCount(): Int {
        return recyclerViewItems.size
    }

    inner class RecyclerViewViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

        private val musicTitle: TextView = itemView.findViewById(R.id.musicTitle)
        private val musicDesc: TextView = itemView.findViewById(R.id.musicDesc)

        fun bind(recyclerViewItem: recyclerViewItem, i: Int) {
            musicTitle.text = recyclerViewItem.music_title
            musicDesc.text = recyclerViewItem.music_artist

            itemView.setOnClickListener {
                Log.d("num", i.toString())
            }
        }

    }

}