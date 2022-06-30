package com.example.learning.data.local

import androidx.room.*

@Dao
interface PosterDatabaseDao {

    @Query("SELECT * FROM poster")
    fun getAll(): List<Poster>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertPosters(vararg posters: Poster)

    @Delete
    fun deletePosters(poster: Poster)
}