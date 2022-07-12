package com.example.learning.utils

import android.graphics.Color
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.core.graphics.drawable.DrawableCompat
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.example.learning.data.model.PosterEntity

@BindingAdapter("ComposeAuthor")
fun TextView.composeAuthor(poster: PosterEntity) {
    this.text = poster.author
}

@BindingAdapter("ComposeDownloads")
fun TextView.composeDownloads(poster: PosterEntity) {
    this.text = poster.postLikes.toString()
}

@BindingAdapter("ComposeImage")
fun ImageView.composeImage(poster: PosterEntity) {
    Glide.with(this.context).load(poster.postUrl).into(this)
}

@BindingAdapter("imageUrl")
fun ImageView.bindImage(imgUrl: String?) {
    imgUrl?.let {
        Glide.with(this.context).load(it).into(this)
    }
}

@BindingAdapter("setColor")
fun View.setColor(poster: PosterEntity) {

}