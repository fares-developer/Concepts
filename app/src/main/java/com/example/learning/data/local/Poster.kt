package com.example.learning.data.local

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Poster(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    @ColumnInfo(name = "pos_author") val author: String?,
    @ColumnInfo(name = "pos_downloads") val postDownloads: Int?,
    @ColumnInfo(name = "pos_path") val postPath:String?
)