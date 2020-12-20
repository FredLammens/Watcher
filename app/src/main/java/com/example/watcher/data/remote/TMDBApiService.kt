package com.example.watcher.data.remote

import com.example.watcher.models.configuration.ConfigurationRespons
import com.example.watcher.models.movies.MovieRespons
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

const val API_KEY = "0c970839aa27dbfd0ab2978ffc1a4aa8"
const val BASE_URL = "https://api.themoviedb.org/"
// https://api.themoviedb.org/3/movie/popular?api_key=0c970839aa27dbfd0ab2978ffc1a4aa8&language=en-US&page=1

interface TMDBApiService {
    //region apiCalls
    /**
     * Returns a MovieResponse wrapped in a response class.
     * @param language language for the movie information default = nl
     * @param page how many pages min:1 max 1000 default = 1
     * @param region ISO 3166-1 code used to filter release dates !Must be UPPERCASE!
     */
    @GET("3/movie/popular")
    suspend fun getPopularMovies(
        @Query("page") page: Int = 1,
        @Query("language") language: String = "nl",
        @Query("region") region: String = "BE"
    ): Response<MovieRespons>

    /**
     * returns the imageconfiguration object used for converting relative img paths to absolute
     */
    @GET("3/configuration")
    suspend fun getImageConfiguration(): Response<ConfigurationRespons>
    //endregion

    companion object { // static method
        operator fun invoke(): TMDBApiService { // invoke voor gewoon object te kunnen gebruiken

            // interceptor voor apiKey dat elke keer moet toegevoegd worden.
            val requestInterceptor = Interceptor { chain ->
                val url = chain.request()
                    .url
                    .newBuilder() // use this builder to add query parameter
                    .addQueryParameter("api_key", API_KEY)
                    .build()
                val request = chain.request() // sets url of builder to updated url
                    .newBuilder()
                    .url(url)
                    .build()
                return@Interceptor chain.proceed(request) // proceeds with created request that contains updated url.
            }
            val logging = HttpLoggingInterceptor()
            logging.setLevel(HttpLoggingInterceptor.Level.BODY)

            val okHttpClient = OkHttpClient.Builder() // add request so it actually intercepts all calls
                .addInterceptor(requestInterceptor)
                .addInterceptor(logging)
                .build()

            return Retrofit.Builder()
                .client(okHttpClient)
                .baseUrl(BASE_URL)
                .addConverterFactory(MoshiConverterFactory.create()) // determine how response should be converted
                .build()
                .create(TMDBApiService::class.java) // actual object to make network requests
        }
    }
}
