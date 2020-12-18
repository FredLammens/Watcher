package com.example.watcher.ui.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.watcher.models.movies.Result

class MovieDetailViewModel: ViewModel(){
    private var _movie = MutableLiveData<Result>() //enkel aanpasbaar binnen viewmodel
    val movie  : LiveData<Result>
        get() = _movie

    //Todo: zij gebruikt volledig element ik heb dit al als id ingesteld
    fun updateMovie(movieId: Int){
        //_movie.value = movie
        // get van repo
    }
}