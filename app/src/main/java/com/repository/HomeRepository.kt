package com.repository

import com.network.RetrofitInstance

class HomeRepository {

suspend fun getPopularMovies() =
    RetrofitInstance.api.getPopularMovies()

    suspend fun getTopRatedMovies() =
        RetrofitInstance.api.getTopRatedMovies()

    suspend fun getUpcomingMovies() =
        RetrofitInstance.api.getUpcomingMovies()

























//    val showProgress = MutableLiveData<Boolean>()
//
//    fun showViewInHome() {
//
//        showProgress.value = true
//        val retrofit = Retrofit.Builder()
//            .baseUrl(BASE_URL)
//            .addConverterFactory(GsonConverterFactory.create())
//            .build()
//
//
//        val service = retrofit.create(MovieNetworkApi::class.java)
//
//        service.getResults().enqueue(object : Callback<List<TopRated>> {
//            override fun onResponse(
//                call: Call<List<TopRated>>,
//                response: Response<List<TopRated>>
//            ) {
//                Toast.makeText(application, "Successful", Toast.LENGTH_SHORT).show()
//                Log.i("SearchRepo", "Response : ${Gson().toJson(response.body())}")
//                showProgress.value = false
//            }
//
//            override fun onFailure(call: Call<List<TopRated>>, t: Throwable) {
//                showProgress.value = false
//                Toast.makeText(application, "Error while accessing the API", Toast.LENGTH_SHORT)
//                    .show()
//            }
//        })
//
//    }
}
