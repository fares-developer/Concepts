package com.example.learning.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Poster::class], version = 1, exportSchema = false)
abstract class PosterDatabase : RoomDatabase() {

    abstract val posterDatabaseDao: PosterDatabaseDao

    companion object {

        @Volatile
        private var INSTANCE: PosterDatabase? = null


        /**
         * @param context hace referencia al contexto de la aplicación.
         * El syschronized es utilizado muy a menudo en programación multihilo, en este caso
         * impide que otro hilo pueda acceder a este método y así sólo existirá una instancia
         * de la base de datos
         */
        fun getInstance(context: Context): PosterDatabase {
            synchronized(this) {
                var instance = INSTANCE

                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        PosterDatabase::class.java,
                        "poster_database"
                    )
                        .fallbackToDestructiveMigration()
                        .build()
                    INSTANCE = instance
                }
                return instance
            }
        }
    }
}