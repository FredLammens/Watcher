package com.example.watcher.ui.viewmodels

import android.telecom.Call
import android.util.Log
import androidx.lifecycle.*
import com.example.watcher.data.remote.TMDBApiService
import com.example.watcher.models.movies.Result
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.lang.Exception
import java.lang.IllegalArgumentException

var relativeImgUrl  = "https://image.tmdb.org/t/p/"
const val IMG_SIZE = "w500"
class OverviewViewModel(private val apiService: TMDBApiService) : ViewModel(){
    private var _movies = MutableLiveData<List<Result>>() //enkel aanpasbaar binnen viewmodel
    val movies  : LiveData<List<Result>>
        get() = _movies

    init{
        getMovies()
    }

    /**
     * sets the viewmodel movies with the api and maps all posterimgs to absolute version
     */
    private fun getMovies(){
        viewModelScope.launch {
            val response = apiService.getPopularMovies(5)
            if(response.isSuccessful){
                _movies.value = response.body()?.results
                _movies.value?.map { it.posterPath = relativeImgUrl + IMG_SIZE + it.posterPath}
            }else{
                Log.e("Api Call failed", response.code().toString())
            }
        }
    }
/*    private suspend fun UpdateRelativeImgUrl(){
        withContext(Dispatchers.Default){
            val response = apiService.getImageConfiguration()
            if(response.isSuccessful)
                relativeImgUrl = response.body().images.secureBaseUrl
        }
    }*/
}