package com.example.learning.data.remote

import com.example.learning.data.model.Poster
import com.example.learning.data.model.Result
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

//Con esto controlamos los estados de las peticiones
enum class PosterApiStatus { LOADING, ERROR, DONE }

//Url base para las peticiones al servicio REST
private const val baseUrl: String = "https://api.unsplash.com/"

private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

//Retrofit necesita mínimo dos cosas para construir una API, la url y una fábrica de convertidores
private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .baseUrl(baseUrl)
    .build()


//Interfaz que define cómo retrofit se comunica con el servicio web
interface PosterApiService {

    @GET("topics/food-drink/photos?per_page=40")
    suspend fun getFoodDrinkPosters(
        @Query("client_id") apiKey: String
    ): List<Poster>
}

object PosterApi {
    val retrofitService: PosterApiService by lazy {
        retrofit.create(PosterApiService::class.java)
    }
}