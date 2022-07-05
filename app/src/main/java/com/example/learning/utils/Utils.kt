package com.example.learning.utils

import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.example.learning.data.model.Poster


@BindingAdapter("ComposeAuthor")
fun TextView.composeAuthor(poster: Poster) {
    this.text = poster.author
}

@BindingAdapter("ComposeDownloads")
fun TextView.composeDownloads(poster: Poster) {
    this.text = poster.postDownloads.toString()
}

@BindingAdapter("ComposeImage")
fun ImageView.composeImage(poster: Poster) {
    Glide.with(this.context).load(poster.postPath).into(this)
}