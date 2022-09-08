package com.haram.miml

import android.annotation.SuppressLint
import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.os.Message
import android.text.Html
import android.text.Spanned
import android.util.Log
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.launch
import org.jsoup.Jsoup
import kotlin.concurrent.thread

class MakeActivity : AppCompatActivity() {
    @SuppressLint("ResourceAsColor")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_make)
//        val desc = findViewById<TextView>(R.id.desc)

        var DescViewItems: ArrayList<DescItem> = ArrayList()

        val DescRecyclerView = findViewById<RecyclerView>(R.id.descRecyclerview)
        DescRecyclerView.layoutManager = LinearLayoutManager(this)

        val adapter = DescViewAdapter(DescViewItems)
        DescRecyclerView.adapter = adapter

        val handler: Handler = @SuppressLint("HandlerLeak")
        object : Handler() {
            @SuppressLint("NotifyDataSetChanged")
            override fun handleMessage(msg: Message) {
                adapter.notifyDataSetChanged()
            }
        }

        val href = intent.getStringExtra("href")
        Log.d("texts", href.toString())

        var doc: String = ""

        thread {
            val searchSite =
                "https://www.melon.com/song/detail.htm?songId=${href}"

            doc = Jsoup.connect(searchSite).get().select("div#d_video_summary").toString()

            Log.d("texts", doc)

            val a: MutableList<String> = doc.split("-->").toMutableList()
            val b: MutableList<String> = a[1].split("<br>").toMutableList()
            Log.d("listss", b.toString())

            for (i in 0 until b.size - 1) {
                DescViewItems.add(
                    DescItem(
                        b[i],
                        false
                    )
                )
            }

            handler.sendMessage(handler.obtainMessage())
        }
    }
}