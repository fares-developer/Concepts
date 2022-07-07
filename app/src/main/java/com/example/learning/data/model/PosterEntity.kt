package com.example.learning.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "poster")
data class PosterEntity(
    @PrimaryKey val id: String = "",
    @ColumnInfo(name = "pos_author") val author: String?,
    @ColumnInfo(name = "pos_downloads") val postDownloads: Int?,
    @ColumnInfo(name = "pos_path") val postPath:String?
)