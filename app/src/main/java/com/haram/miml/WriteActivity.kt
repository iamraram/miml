package com.haram.miml

import android.os.Bundle
import android.util.Log
import android.view.inputmethod.InputMethodManager
import android.widget.SearchView
import androidx.appcompat.app.AppCompatActivity
import org.jsoup.Jsoup

class WriteActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_write)

        val search = findViewById<SearchView>(R.id.search)

        val imm: InputMethodManager = getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
        imm.showSoftInput(search, 0)

        fun parsing(query: String?) {
            Thread {
                val searchSite = "https://www.melon.com/search/song/index.htm?q=${query}&section=&searchGnbYn=Y&kkoSpl=N&kkoDpType="
                val doc = Jsoup.connect(searchSite).get().select("tbody tr td.t_left div.wrap.pd_none div.eclipse a.fc_gray")

                Log.d("texts", searchSite)
                Log.d("texts", doc.toString())
            }.start()
        }

        search.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                Log.d("text", 10.toString())
                parsing(query)
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return true
            }
        })
    }
}