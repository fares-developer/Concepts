package com.example.learning.data.local

import androidx.lifecycle.LiveData
import com.example.learning.data.model.PosterEntity

class LocalDataSource(private val database:PosterDatabaseDao) {

     fun getAllPosters(): LiveData<List<PosterEntity>> = database.getAllPosters()

     suspend fun insertPoster(poster: PosterEntity) {
         database.insertPoster(poster)
    }

}