package com.example.learning.repository

import com.example.learning.data.local.PosterDatabaseDao

/**
 * Esta clase será la encargada de implementar los métodos de los repos local y remoto,
 * lo que haremos aquí es llamar al repo remoto obtener los datos de él, guardarlos en
 * el repo local para cumplir el principio single source truth, y finalmente la capa de presentación
 * hará sus peticiones a esta clase para obtener los datos
 */
class PosterRepoImp(private val localDataSource:PosterDatabaseDao) {

}