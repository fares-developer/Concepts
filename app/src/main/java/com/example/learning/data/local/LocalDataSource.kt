package com.example.learning.data.local

import androidx.lifecycle.LiveData
import com.example.learning.data.model.Poster

class LocalDataSource(private val localSource:PosterDatabaseDao) {

     fun getAllPosters(): LiveData<List<Poster>> = localSource.getAllPosters()

     suspend fun insertPoster(poster: Poster) {
        postersList.forEach {
            localSource.insertPoster(it)
        }
    }

     suspend fun deletePosters(posters: Poster) {
        postersList.forEach {
            localSource.deletePosters(it)
        }
    }

    private val postersList: List<Poster> = listOf(
        Poster(
            id = 0L,
            author = "Charles Deluvio", postDownloads = 117559,
            postPath = "https://images.unsplash.com/photo-1517282009859-f000ec3b26fe?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=387&q=80"
        ),

        Poster(
            id = 1L,
            author = "Marcus Wallis", postDownloads = 13576,
            postPath = "https://images.unsplash.com/photo-1526865906320-0200a6e2c7f0?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=435&q=80"
        ),

        Poster(
            id = 2L,
            author = "Mae Mu", postDownloads = 111915,
            postPath = "https://images.unsplash.com/photo-1552010099-5dc86fcfaa38?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=464&q=80"
        ),

        Poster(
            id = 3L,
            author = "Tagerine Newt", postDownloads = 24956,
            postPath = "https://images.unsplash.com/photo-1618897996318-5a901fa6ca71?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=464&q=80"
        ),

        Poster(
            id = 4L,
            author = "Melisa Belanger", postDownloads = 11628,
            postPath = "https://images.unsplash.com/photo-1594002348772-bc0cb57ade8b?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=387&q=80"
        ),

        Poster(
            id = 5L,
            author = "Birgith Roosipuu", postDownloads = 7213,
            postPath = "https://images.unsplash.com/photo-1597269391563-7c7837196d69?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=387&q=80"
        )
    )
}