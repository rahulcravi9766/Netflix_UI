package com.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.model.Result
import com.model.TopRated
import com.repository.HomeRepository
import com.util.Resource
import kotlinx.coroutines.launch

class MovieDetailsViewModel( var fullDetails: Result) : ViewModel() {


    val name : MutableLiveData<String> = MutableLiveData()
    val poster : MutableLiveData<String> = MutableLiveData()
    val backPoster : MutableLiveData<String> = MutableLiveData()
    val overview : MutableLiveData<String> = MutableLiveData()
    val rating : MutableLiveData<String> = MutableLiveData()

    init {
        movieName()
        moviePoster()
        movieBackPoster()
        movieOverview()
        movieRating()

    }

    private fun movieName() = viewModelScope.launch {
        name.value = fullDetails.title
    }

    private fun moviePoster() = viewModelScope.launch {
        poster.value = fullDetails.poster_path
    }

    private fun movieBackPoster() = viewModelScope.launch {
        backPoster.value = fullDetails.backdrop_path
    }

    private fun movieOverview() = viewModelScope.launch {
        overview.value = fullDetails.overview
    }

    private fun movieRating() = viewModelScope.launch {
        rating.value = fullDetails.vote_average.toString()
    }



}