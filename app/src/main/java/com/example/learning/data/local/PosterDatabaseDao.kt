package com.example.learning.data.local

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface PosterDatabaseDao {

    @Query("SELECT * FROM poster")
    fun getAllPosters(): LiveData<List<Poster>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPoster(poster: Poster)

    @Delete
    suspend fun deletePosters(posters: Poster)
}