package com.haram.miml

import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.SearchView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import org.jsoup.Jsoup
import kotlin.concurrent.thread

class WriteActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_write)

        val search = findViewById<SearchView>(R.id.search)

        val imm: InputMethodManager = getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
        imm.showSoftInput(search, 0)

        val searchRecyclerviewItems: ArrayList<searchViewItem> = ArrayList()

        val searchRecyclerview = findViewById<RecyclerView>(R.id.searchRecyclerView)
        searchRecyclerview.layoutManager = LinearLayoutManager(this)

        val adapter = RecyclerViewAdapter(searchViewItems)
        searchRecyclerview.adapter = adapter

        search.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                Log.d("text", 10.toString())

                val a: MutableList<String> = mutableListOf()
                val b: MutableList<String> = mutableListOf()

                var size: Int = 0

                thread {
                    val searchSite = "https://www.melon.com/search/song/index.htm?q=${query}&section=&searchGnbYn=Y&kkoSpl=N&kkoDpType="
                    val doc = Jsoup.connect(searchSite).get().select("div.pd_none div.ellipsis")
                    val doc2 = Jsoup.connect(searchSite).get().select("div.wrap div#artistName")

                    doc.forEachIndexed{ _, elem ->
                        a.add(elem.select("a.fc_gray").attr("title"))
                    }

                    doc2.forEachIndexed{ _, elem ->
                        b.add(elem.select("a.fc_gray").text())
                    }

                    size = a.size

                    Log.d("texts", searchSite)
                    Log.d("texts", a.toString() + b.toString())
                }

                for (i in 0 .. size) {
                    searchRecyclerviewItems.add(
                        searchViewItem(
                            a[i],
                            b[i]
                        )
                    )
                }

                searchRecyclerview.visibility = View.VISIBLE
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return true
            }
        })
    }
}