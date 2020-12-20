package com.example.watcher.ui.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.watcher.data.MoviesRepository
import com.example.watcher.models.movies.MovieRespons
import com.example.watcher.models.movies.Result
import com.example.watcher.utils.Resource
import kotlinx.coroutines.launch
import retrofit2.Response

var relativeImgUrl = "https://image.tmdb.org/t/p/"
const val IMG_SIZE = "w500"
class OverviewViewModel(private val repo: MoviesRepository) : ViewModel() {
    private var _movies = MutableLiveData<Resource<List<Result>>>() // enkel aanpasbaar binnen viewmodel
    val movies: LiveData<Resource<List<Result>>>
        get() = _movies

    init {
        getMovies()
    }

    /**
     * sets the viewmodel movies with the api and maps all posterimgs to absolute version
     */
    private fun getMovies() {
        viewModelScope.launch {
            _movies.postValue(Resource.Loading())
            val response = repo.getPopularMovies(5)
            _movies.postValue(handleMoviesResponse(response))
        }
    }

    private suspend fun handleMoviesResponse(response: Response<MovieRespons>): Resource<List<Result>> {
        if (response.isSuccessful) {
            val results = response.body()!!.results.map { it.apply { it.posterPath = relativeImgUrl + IMG_SIZE + it.posterPath } }
            repo.insertMoviesIntoDB(results)
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
