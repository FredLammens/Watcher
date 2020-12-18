package com.example.watcher.data.remote

import com.example.watcher.models.configuration.ConfigurationRespons
import com.example.watcher.models.movies.MovieRespons
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

const val API_KEY = "0c970839aa27dbfd0ab2978ffc1a4aa8"
//https://api.themoviedb.org/3/movie/popular?api_key=0c970839aa27dbfd0ab2978ffc1a4aa8&language=en-US&page=1

interface TMDBApiService {
    /**
     * Returns a MovieResponse wrapped in a response class.
     * @param  language language for the movie information default = nl
     * @param page how many pages min:1 max 1000 default = 1
     * @param region ISO 3166-1 code used to filter release dates !Must be UPPERCASE!
     */
    @GET("/movie/popular")
    suspend fun getPopularMovies(
            @Query("language") language:String = "nl",
            @Query("page") page:Int = 1,
            @Query("region") region:String = "BE"
    ) : Response<MovieRespons>

    @GET("/configuration")
    suspend fun getImageConfiguration(
    ) : Response<ConfigurationRespons>

}