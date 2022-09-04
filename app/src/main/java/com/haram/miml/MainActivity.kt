package com.haram.miml

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val addBtn = findViewById<TextView>(R.id.addBtn)
        addBtn.setOnClickListener {
            val intent = Intent(this, WriteActivity::class.java)
            startActivity(intent)
        }

        val recyclerViewItems: ArrayList<recyclerViewItem> = ArrayList()

        for (i in 0 .. 15) {
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
        }

        val recyclerView = findViewById<RecyclerView>(R.id.musicRecyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)

        val adapter = RecyclerViewAdapter(recyclerViewItems)
        recyclerView.adapter = adapter
    }
}