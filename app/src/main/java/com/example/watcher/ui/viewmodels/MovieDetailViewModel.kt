package com.example.watcher.ui.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.watcher.data.MoviesRepository
import com.example.watcher.models.movies.Result

class MovieDetailViewModel(repo: MoviesRepository, movieId: Int): ViewModel(){
    private var _movie : LiveData<Result> = repo.getMovieWithId(movieId)//enkel aanpasbaar binnen viewmodel
    val movie  : LiveData<Result>
        get() = _movie
}