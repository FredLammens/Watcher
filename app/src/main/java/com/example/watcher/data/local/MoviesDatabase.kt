package com.example.watcher.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.watcher.models.movies.Result

@Database(
        entities = [Result::class],
        version = 1
)
@TypeConverters(Converters::class)
abstract class MoviesDatabase : RoomDatabase(){

    //abstract fun getMoviesDao(): MovieDao


    companion object{ //creates actual database

        @Volatile private var instance: MoviesDatabase? = null //volatile = other threads see when instance changes / instance = singleton
        private val LOCK = Any()

        operator fun invoke(context: Context) = instance ?: synchronized(LOCK){//synchronize lock makes sure theres only one database/ not another threads that sets this
            instance ?: createDatabase(context).also{instance=it} //if instance = null creates database
        }
        private fun createDatabase(context: Context) = Room.databaseBuilder(
                context.applicationContext,
                MoviesDatabase::class.java,
                "moviesDB"
        ).build()
    }
}