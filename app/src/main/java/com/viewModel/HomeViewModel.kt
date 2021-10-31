package com.viewModel

import androidx.lifecycle.*
import com.model.TopRated
import com.repository.HomeRepository
import com.util.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Response

class HomeViewModel(
    private val repository: HomeRepository
) : ViewModel() {

    val popularMovies : MutableLiveData<Resource<TopRated>> = MutableLiveData()
    val topRatedMovies : MutableLiveData<Resource<TopRated>> = MutableLiveData()
    val upcomingMovies : MutableLiveData<Resource<TopRated>> = MutableLiveData()
    val mainMoviePoster : MutableLiveData<Resource<String>> = MutableLiveData()

    init {
        getPopularMovies()
        getTopRatedMovies()
        getUpcomingMovies()
        getMainMoviePoster()
    }

    private fun getPopularMovies() = viewModelScope.launch(Dispatchers.IO) {
        popularMovies.postValue(Resource.Loading())
        val response = repository.getPopularMovies()
        popularMovies.postValue(handleMovieResponse(response))
    }

    private fun getTopRatedMovies() = viewModelScope.launch(Dispatchers.IO) {
        topRatedMovies.postValue(Resource.Loading())
        val response = repository.getTopRatedMovies()
        topRatedMovies.postValue(handleMovieResponse(response))
    }

    private fun getUpcomingMovies() = viewModelScope.launch(Dispatchers.IO) {
        upcomingMovies.postValue(Resource.Loading())
        val response = repository.getUpcomingMovies()
        upcomingMovies.postValue(handleMovieResponse(response))
    }

    private fun getMainMoviePoster() = viewModelScope.launch(Dispatchers.IO) {
        mainMoviePoster.postValue(Resource.Loading())
        val response = repository.getUpcomingMovies()
        mainMoviePoster.postValue(handleImageResponse(response))
    }

    private fun handleMovieResponse(response: Response<TopRated>) : Resource<TopRated>{

        if(response.isSuccessful){
            response.body()?.let{ resultResponse ->
                return  Resource.Success(resultResponse)
            }
        }
        return  Resource.Error(response.message())
    }

    private fun handleImageResponse(response: Response<TopRated>) : Resource<String>{

        if(response.isSuccessful){
            response.body()?.let{ resultResponse ->
                return  Resource.Success("https://image.tmdb.org/t/p/w500${resultResponse.results.get(2).poster_path}")
            }
        }
        return  Resource.Error(response.message())
    }
}

