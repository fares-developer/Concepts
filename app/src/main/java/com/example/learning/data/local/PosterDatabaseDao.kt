package com.example.learning.data.local

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface PosterDatabaseDao {

    @Query("SELECT * FROM poster")
    fun getAllPosters(): LiveData<List<Poster>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPosters(vararg posters: Poster)

    @Delete
    suspend fun deletePosters(poster: Poster)
}