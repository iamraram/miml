package com.haram.miml

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val recyclerViewItems: ArrayList<recyclerViewItem> = ArrayList()
        recyclerViewItems.add(
            recyclerViewItem(
                "Sneakers",
                "ITZY(있지)",
                "© JYP ent.",
                false,
                null,
                null,
                null
            )
        )
        recyclerViewItems.add(
            recyclerViewItem(
                "안녕, 곰인형",
                "볼빨간사춘기",
                "© Shofar Music",
                false,
                null,
                null,
                null
            )
        )

        val recyclerView = findViewById<RecyclerView>(R.id.musicRecyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)

        val adapter = RecyclerViewAdapter(recyclerViewItems)
        recyclerView.adapter = adapter
    }
}