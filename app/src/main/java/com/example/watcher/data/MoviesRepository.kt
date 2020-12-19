package com.example.watcher.data

import com.example.watcher.data.local.MoviesDatabase
import com.example.watcher.data.remote.TMDBApiService

class MoviesRepository(val db: MoviesDatabase) {
    suspend fun getPopularMovies(amount:Int,language:String = "nl",region:String = "BE" ) =
        TMDBApiService().getPopularMovies(amount,language,region)
}