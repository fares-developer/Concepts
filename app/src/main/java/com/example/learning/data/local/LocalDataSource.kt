package com.example.learning.data.local

import androidx.lifecycle.LiveData
import com.example.learning.data.model.PosterEntity

class LocalDataSource(private val database:PosterDatabaseDao) {

     suspend fun getAllPosters(): List<PosterEntity> = database.getAllPosters()

     suspend fun insertPoster(poster: PosterEntity) {
         database.insertPoster(poster)
    }

}