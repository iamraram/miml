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

        val desc = findViewById<TextView>(R.id.desc)

        val href = intent.getStringExtra("href")
        Log.d("texts", href.toString())

        var doc: String = ""
        var bool: Boolean = false

        thread {
            val searchSite =
                "https://www.melon.com/song/detail.htm?songId=${href}"

            doc = Jsoup.connect(searchSite).get().select("div#d_video_summary").toString()

            Log.d("texts", doc)
            bool = true

            fun String.toSpanned(): Spanned {
                return Html.fromHtml(this, Html.FROM_HTML_MODE_LEGACY)
            }

            val a = doc.toSpanned().toString()
            Log.d("!!!!!", doc.toSpanned().toString())

            CoroutineScope(Main).launch {
                desc.setTextColor(R.color.text)
                desc.text = a
                // do something
            }

        }

        Log.d("bools", "3 " + bool.toString())
    }
}