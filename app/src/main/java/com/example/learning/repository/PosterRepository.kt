package com.example.learning.repository

import androidx.lifecycle.LiveData
import com.example.learning.data.model.Poster

interface PosterRepository {

    suspend fun getAllPosters(): List<Poster>
    suspend fun insertPosters():List<Poster>
}