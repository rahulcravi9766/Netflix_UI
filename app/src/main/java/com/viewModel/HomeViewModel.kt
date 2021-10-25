package com.viewModel

import android.app.Application
import androidx.lifecycle.*
import com.model.Result
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

    init {
        getPopularMovies()
        getTopRatedMovies()
        getUpcomingMovies()
    }

    private fun getPopularMovies() = viewModelScope.launch(Dispatchers.IO) {
        popularMovies.postValue(Resource.Loading())
        val response = repository.getPopularMovies()
        popularMovies.postValue(handlePopularMovieResponse(response))

    }

    private fun getTopRatedMovies() = viewModelScope.launch(Dispatchers.IO) {
        topRatedMovies.postValue(Resource.Loading())
        val response = repository.getTopRatedMovies()
        topRatedMovies.postValue(handlePopularMovieResponse(response))

    }

    private fun getUpcomingMovies() = viewModelScope.launch(Dispatchers.IO) {
        upcomingMovies.postValue(Resource.Loading())
        val response = repository.getUpcomingMovies()
        upcomingMovies.postValue(handlePopularMovieResponse(response))

    }

    private fun handlePopularMovieResponse(response: Response<TopRated>) : Resource<TopRated>{

        if(response.isSuccessful){

            response.body()?.let{ resultResponse ->
                return  Resource.Success(resultResponse)
            }
        }
        return  Resource.Error(response.message())
    }
}

