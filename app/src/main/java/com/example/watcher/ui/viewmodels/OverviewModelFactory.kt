package com.example.watcher.ui.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.watcher.data.MoviesRepository
import com.example.watcher.data.remote.TMDBApiService
import java.lang.IllegalArgumentException

@Suppress("UNCHECKED_CAST")
class OverviewModelFactory (private val repo: MoviesRepository): ViewModelProvider.Factory{
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(OverviewViewModel::class.java)){
            return OverviewViewModel(repo) as T
        }
        throw IllegalArgumentException("unknown viewmodel class")
    }

}