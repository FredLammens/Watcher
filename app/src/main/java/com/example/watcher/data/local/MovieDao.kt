package com.example.watcher.data.local

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.watcher.models.movies.Result

@Dao
interface MovieDao { //zoals apiService
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsert(result:Result) //update or insert geeft id dat insert is terug

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsertAll(movies:List<Result>)

    @Query("SELECT * FROM movies")
    fun getAllMovies(): LiveData<List<Result>>

    @Query("SELECT * FROM movies WHERE id = :id ")
    fun getMovie(id: Int) : LiveData<Result>
}