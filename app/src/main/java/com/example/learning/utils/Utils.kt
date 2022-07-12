package com.example.learning.utils

import android.view.View
import com.example.learning.data.model.Poster
import com.example.learning.data.model.PosterEntity

fun Poster.toPosterEntity(): PosterEntity = PosterEntity(
    id = this.id,
    author = this.user.name,
    postLikes = this.likes,
    postUrl = this.urls.full,
    postColorImage = this.color,
    postDescription = this.description
)

fun List<Poster>.toPosterEntityList(): List<PosterEntity> {
    val arr = ArrayList<PosterEntity>()
    this.forEach {
        arr.add(it.toPosterEntity())
        it.toPosterEntity()
    }
    return arr.toList()
}

fun View.show(view1: View? = null, view2: View? = null, hideOthers: Boolean = false) {

    this.visibility = View.VISIBLE

    if (hideOthers) {
        view1?.hide()
        view2?.hide()
    }
}

fun View.hide() { this.visibility = View.GONE }