package com.haram.miml

import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView

class SearchViewAdapter(private val searchViewItems : ArrayList<searchViewItem>): RecyclerView.Adapter<SearchViewAdapter.searchRecyclerviewViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): searchRecyclerviewViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.searchview, parent, false)
        return searchRecyclerviewViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: searchRecyclerviewViewHolder, position: Int) {
        holder.bind(searchViewItems[position], position)
    }

    override fun getItemCount(): Int {
        return searchViewItems.size
    }

    inner class searchRecyclerviewViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

        private val musicTitle: TextView = itemView.findViewById(R.id.musicTitle)
        private val musicDesc: TextView = itemView.findViewById(R.id.musicDesc)


        fun bind(searchViewItem: searchViewItem, i: Int) {
            musicTitle.text = searchViewItem.music_title
            musicDesc.text = searchViewItem.music_artist

            val intent = Intent(itemView.context, MakeActivity::class.java)

            itemView.setOnClickListener {
                Log.d("texts", i.toString())
                intent.putExtra("href", searchViewItem.href)
                itemView.context.startActivity(intent)
            }
        }

    }

}