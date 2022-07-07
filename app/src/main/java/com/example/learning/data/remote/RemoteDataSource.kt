package com.example.learning.data.remote

import com.example.learning.data.model.Poster
import com.example.learning.utils.apiKey

/**
 * Esta clase es la encargada de hacer las consultas al servidor
 * @param webService que es la instancia de nuestra interfaz ApiService donde está retrofit
 * y las llamadas de red.
 */
class RemoteDataSource(private val webService: PosterApiService) {

    suspend fun getFoodDrinkPosters(): List<Poster> =
        webService.getFoodDrinkPosters(apiKey)

    suspend fun getArquitectPosters() {
        TODO()
    }

    suspend fun getBusinessWorkPosters() {
        TODO()
    }
}