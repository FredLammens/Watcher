package com.example.watcher.ui.viewmodels

import android.telecom.Call
import android.util.Log
import androidx.lifecycle.*
import com.example.watcher.data.MoviesRepository
import com.example.watcher.data.remote.TMDBApiService
import com.example.watcher.models.movies.MovieRespons
import com.example.watcher.models.movies.Result
import com.example.watcher.utils.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Response
import java.lang.Exception
import java.lang.IllegalArgumentException

var relativeImgUrl  = "https://image.tmdb.org/t/p/"
const val IMG_SIZE = "w500"
class OverviewViewModel(private val repo: MoviesRepository) : ViewModel(){
    private var _movies = MutableLiveData<Resource<List<Result>>>() //enkel aanpasbaar binnen viewmodel
    val movies  : LiveData<Resource<List<Result>>>
        get() = _movies

    init{
        getMovies()
    }

    /**
     * sets the viewmodel movies with the api and maps all posterimgs to absolute version
     */
    private fun getMovies(){
        viewModelScope.launch {
            _movies.postValue(Resource.Loading())
            val response = repo.getPopularMovies(5)
            _movies.postValue(handleMoviesResponse(response))
        }
    }

    private fun handleMoviesResponse(response: Response<MovieRespons>) : Resource<List<Result>> {
        if(response.isSuccessful){
            val results = response.body()!!.results.map { it -> it.apply {it.posterPath = relativeImgUrl + IMG_SIZE + it.posterPath}}
            return Resource.Succes(results)
        }
        return Resource.Error(response.message())
    }

/*    private suspend fun UpdateRelativeImgUrl(){
        withContext(Dispatchers.Default){
            val response = apiService.getImageConfiguration()
            if(response.isSuccessful)
                relativeImgUrl = response.body().images.secureBaseUrl
        }
    }*/
}