package com.example.watcher.ui.viewmodels

import android.util.Log
import androidx.lifecycle.*
import com.example.watcher.data.remote.TMDBApiService
import com.example.watcher.models.movies.Result
import kotlinx.coroutines.launch
import java.lang.Exception
import java.lang.IllegalArgumentException

class OverviewViewModel(private val apiService: TMDBApiService) : ViewModel(){
    private var _movies = MutableLiveData<List<Result>>() //enkel aanpasbaar binnen viewmodel
    val movies  : LiveData<List<Result>>
        get() = _movies

    init{
        getMovies()
    }
    private fun getMovies(){
        viewModelScope.launch {
            try{
                val response = apiService.getPopularMovies(5)
                    _movies.value = response.results
            }catch (e:Exception){
                Log.e("Error Api", e.message,e)
            }

        }
    }
}