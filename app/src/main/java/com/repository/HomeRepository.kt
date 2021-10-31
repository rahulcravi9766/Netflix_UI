package com.repository

import com.network.RetrofitInstance

class HomeRepository {

suspend fun getPopularMovies() =
    RetrofitInstance.api.getPopularMovies()

    suspend fun getTopRatedMovies() =
        RetrofitInstance.api.getTopRatedMovies()

    suspend fun getUpcomingMovies() =
        RetrofitInstance.api.getUpcomingMovies()

}
