package com.haram.miml

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.os.Handler
import android.os.Message
import android.util.Log
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.SearchView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import org.jsoup.Jsoup
import java.lang.IndexOutOfBoundsException
import kotlin.concurrent.thread

class WriteActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_write)

        val search = findViewById<SearchView>(R.id.search)

        val imm: InputMethodManager = getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
        imm.showSoftInput(search, 0)

        val searchViewItems: ArrayList<searchViewItem> = ArrayList()

        val searchRecyclerview = findViewById<RecyclerView>(R.id.searchRecyclerView)
        searchRecyclerview.layoutManager = LinearLayoutManager(this)

        val adapter = SearchViewAdapter(searchViewItems)
        searchRecyclerview.adapter = adapter

        val handler: Handler = @SuppressLint("HandlerLeak")
        object: Handler() {
            @SuppressLint("NotifyDataSetChanged")
            override fun handleMessage(msg: Message) {
                adapter.notifyDataSetChanged()
            }
        }

        search.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                val a: MutableList<String> = mutableListOf()
                val b: MutableList<String> = mutableListOf()
                val c: MutableList<String> = mutableListOf()
                val c2: MutableList<String> = mutableListOf()

                var size: Int = 0

                thread {
                    Log.d("href2", query.toString())
                    val searchSite = "https://www.melon.com/search/song/index.htm?q=${query}&section=&searchGnbYn=Y&kkoSpl=N&kkoDpType="
                    val doc = Jsoup.connect(searchSite).get().select("div.pd_none div.ellipsis")
                    val doc2 = Jsoup.connect(searchSite).get().select("div.wrap div#artistName span")
                    val doc3 = Jsoup.connect(searchSite).get().select("div.songTypeOne table tbody tr td div.wrap.pd_none")

                    doc.forEachIndexed{ _, elem ->
                        a.add(elem.select("a.fc_gray").attr("title"))
                    }

                    doc2.forEachIndexed{ _, elem ->
                        b.add(elem.select("a.fc_mgray").text())
                    }

                    doc3.forEachIndexed{ _, elem ->
                        c.add(elem.select("input.input_check").attr("value"))
                    }

                    size = a.size
                    searchViewItems.clear()

                    for (i in 0 until c.size) {
                        if (i % 4 == 0) {
                            c2.add(c[i])
                        }
                    }

                    Log.d("href", c2.toString())

                    for (i in 0 until 15) {
                        try {
                            searchViewItems.add(
                                searchViewItem(
                                    a[i],
                                    b[i],
                                    c2[i]
                                )
                            )
                        }
                        catch (e: IndexOutOfBoundsException) {
                            break
                        }
                    }

//                    if (searchViewItems.size == 0) {
//                        txt.visibility = View.VISIBLE
//                        searchRecyclerview.visibility = View.INVISIBLE
//                    }
//                    else {
//                        txt.visibility = View.INVISIBLE
                        searchRecyclerview.visibility = View.VISIBLE

                        handler.sendMessage(handler.obtainMessage())

                        Log.d("texts", searchSite)
                        Log.d("texts", a.toString())
                        Log.d("texts", b.toString())
//                    }
                }
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return true
            }
        })
    }
}