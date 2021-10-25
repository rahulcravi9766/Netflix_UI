package com.network

import com.model.TopRated
import com.util.Constants.Companion.API_KEY
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieApi {
    //we write all the functions to access the api
    @GET("popular?")
    suspend fun getPopularMovies(
        @Query("api_key") apiKey: String = API_KEY
    ): Response<TopRated>

    @GET("top_rated?")
    suspend fun getTopRatedMovies(
        @Query("api_key") apiKey: String = API_KEY
    ): Response<TopRated>

    @GET("upcoming?")
    suspend fun getUpcomingMovies(
        @Query("api_key") apiKey: String = API_KEY
    ): Response<TopRated>
}


