package com.example.watcher.data

import com.example.watcher.data.local.MoviesDatabase
import com.example.watcher.data.remote.TMDBApiService
import com.example.watcher.models.movies.Result

class MoviesRepository(private val db: MoviesDatabase) {
    suspend fun getPopularMovies(amount:Int,language:String = "nl",region:String = "BE" ) =
        TMDBApiService().getPopularMovies(amount,language,region)
    suspend fun insertMoviesIntoDB(movies:List<Result>) = db.getMoviesDao().upsertAll(movies)
    fun getMovieWithId(movieId:Int) = db.getMoviesDao().getMovie(movieId)
}