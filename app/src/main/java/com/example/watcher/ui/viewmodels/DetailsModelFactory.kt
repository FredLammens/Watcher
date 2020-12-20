package com.example.watcher.ui.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.watcher.data.MoviesRepository
import java.lang.IllegalArgumentException

@Suppress("UNCHECKED_CAST")
class DetailsModelFactory (private val repo: MoviesRepository,val id: Int): ViewModelProvider.Factory{
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            if(modelClass.isAssignableFrom(MovieDetailViewModel::class.java)){
                return MovieDetailViewModel(repo,id) as T
            }
            throw IllegalArgumentException("unknown viewmodel class")
        }

}