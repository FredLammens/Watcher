package com.example.watcher.ui.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.watcher.data.remote.TMDBApiService
import java.lang.IllegalArgumentException

class OverviewModelFactory (private val apiService: TMDBApiService): ViewModelProvider.Factory{
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(OverviewViewModel::class.java)){
            return OverviewViewModel(apiService) as T
        }
        throw IllegalArgumentException("unknown viewmodel class")
    }

}