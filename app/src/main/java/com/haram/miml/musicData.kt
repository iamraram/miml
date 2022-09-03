package com.haram.miml

data class recyclerViewItem(
    val music_title: String,
    val music_artist: String,
    val music_copylight: String,
    val is_hearted: Boolean,
    val music_highlighted: MutableList<String>?,
    val music_diary_desc: MutableList<String>?,
    val music_diary_date: MutableList<String>?,
)