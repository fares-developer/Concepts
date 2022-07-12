package com.example.learning.repository

import com.example.learning.data.local.LocalDataSource
import com.example.learning.data.model.PosterEntity
import com.example.learning.data.remote.RemoteDataSource
import com.example.learning.utils.toPosterEntity

/**
 * Esta clase será la encargada de implementar los métodos de los repos local y remoto,
 * lo que haremos aquí es llamar al repo remoto obtener los datos de él, guardarlos en
 * el repo local para cumplir el principio single source truth, y finalmente la capa de presentación
 * hará sus peticiones a esta clase para obtener los datos
 */
class PosterRepositoryImp(
    private val remoteDataSource: RemoteDataSource,
    private val localDatasource: LocalDataSource

) : PosterRepository {

    override suspend fun getAllPosters(): List<PosterEntity> {
        val lista = arrayListOf<PosterEntity>()
        localDatasource.getAllPosters().forEach {
            lista.add(it)
        }
        return lista.toList()
    }

    override suspend fun insertPosters() {
        remoteDataSource.getFoodDrinkPosters().forEach {
            localDatasource.insertPoster(it.toPosterEntity())
        }
    }

    //Esto método se encarga de pasar el poster con el id que se le pasa
    suspend fun getPoster(identifier: String): PosterEntity {
        return getAllPosters().filter { it.id.equals(identifier) }[0]
    }
}