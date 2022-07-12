package com.example.learning.repository

import androidx.lifecycle.LiveData
import com.example.learning.data.model.Poster
import com.example.learning.data.model.PosterEntity

interface PosterRepository {

    suspend fun getAllPosters(): List<PosterEntity>
    suspend fun insertPosters()
}