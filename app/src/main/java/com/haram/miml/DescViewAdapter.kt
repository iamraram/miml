package com.haram.miml

import android.annotation.SuppressLint
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class DescViewAdapter(private val DescViewItems: ArrayList<DescItem>): RecyclerView.Adapter<DescViewAdapter.DescRecyclerViewViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DescRecyclerViewViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.descview, parent, false)
        return DescRecyclerViewViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: DescRecyclerViewViewHolder, position: Int) {
        holder.bind(DescViewItems[position], position)
    }

    override fun getItemCount(): Int {
        return DescViewItems.size
    }

    inner class DescRecyclerViewViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        private var count: Int = 1
        private val desc2: TextView = itemView.findViewById(R.id.musicTitle)

        @SuppressLint("ResourceAsColor")
        fun bind(DescItem: DescItem, i: Int) {
            desc2.text = DescItem.desc

            itemView.setOnClickListener {
                count += 1
                DescItem.isChecked = count % 2 == 0
                if (!DescItem.isChecked) {
                    desc2.setTextColor(R.color.gray)
                    Log.d("va: gray로", DescItem.isChecked.toString())
                }
                else {
                    desc2.setTextColor(R.color.text)
                    Log.d("va: textcolor로", DescItem.isChecked.toString())
                }
                Log.d("va: 1", i.toString() + "번째를 눌렀고 상태는 " + DescItem.isChecked.toString())
                Log.d("va: 2", i.toString() + "의 카운트는 " + count.toString())
            }
        }

    }

}