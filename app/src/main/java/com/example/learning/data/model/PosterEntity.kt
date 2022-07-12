package com.example.learning.data.model

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Suppress("DEPRECATED_ANNOTATION")
@Parcelize //Permite que este objeto se puede compartir entre fragmentos
@Entity(tableName = "poster")
data class PosterEntity(
    @PrimaryKey val id: String = "",
    @ColumnInfo(name = "pos_author") val author: String?,
    @ColumnInfo(name = "pos_likes") val postLikes: Int?,
    @ColumnInfo(name = "pos_url") val postUrl:String?,
    @ColumnInfo(name = "pos_color_img") val postColorImage: String?,
    @ColumnInfo(name = "pos_description") val postDescription:String?
) : Parcelable