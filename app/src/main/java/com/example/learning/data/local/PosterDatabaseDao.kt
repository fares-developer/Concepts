package com.example.learning.data.local

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.learning.data.model.PosterEntity

@Dao
interface PosterDatabaseDao {

    @Query("SELECT * FROM poster")
    suspend fun getAllPosters(): List<PosterEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPoster(poster: PosterEntity)

    @Delete
    suspend fun deletePosters(posters: PosterEntity)
}