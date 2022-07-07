package com.example.learning.utils

import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.example.learning.data.model.PosterEntity

@BindingAdapter("ComposeAuthor")
fun TextView.composeAuthor(poster: PosterEntity) {
    this.text = poster.author
}

@BindingAdapter("ComposeDownloads")
fun TextView.composeDownloads(poster: PosterEntity) {
    this.text = poster.postDownloads.toString()
}

@BindingAdapter("ComposeImage")
fun ImageView.composeImage(poster: PosterEntity) {
    Glide.with(this.context).load(poster.postPath).into(this)
}

/*@BindingAdapter("ListenStatus")
fun CardView.ListenStatus(status: PosterApiStatus? = PosterApiStatus.LOADING) {
    when (status) {
        PosterApiStatus.ERROR -> {
            this.getChildAt(1).show(this.getChildAt(0), this.getChildAt(2), true)
        }
        PosterApiStatus.DONE -> {
            this.getChildAt(2).show(this.getChildAt(0), this.getChildAt(1), true)
        }
        else -> { this.getChildAt(0).show(this.getChildAt(1), this.getChildAt(2),true) }
    }
}*/
