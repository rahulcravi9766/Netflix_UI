package com.view

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.adapters.MovieAdapter
import com.bumptech.glide.Glide
import com.model.Result
import com.model.TopRated
import com.moviehub.MainActivity
import com.moviehub.R
import com.moviehub.databinding.FragmentHomeBinding
import com.repository.HomeRepository
import com.util.Resource
import com.viewModel.HomeViewModel

class HomeFragment : Fragment() {

    private lateinit var popularMoviesRv: RecyclerView
    private lateinit var topRatedMoviesRv: RecyclerView
    private lateinit var upcomingMoviesRv: RecyclerView
    private lateinit var navController: NavController
    private lateinit var viewModel: HomeViewModel
    lateinit var homeBinding: FragmentHomeBinding
    lateinit var popularAdapter: MovieAdapter
    lateinit var topRatedAdapter: MovieAdapter
    lateinit var upcomingAdapter: MovieAdapter
    private val TAG = "HomeFragment"

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        val homeRepository = HomeRepository()
        val viewModelProviderFactory = HomeViewModelProviderFactory(homeRepository)
        viewModel = ViewModelProvider(this, viewModelProviderFactory).get(HomeViewModel::class.java)

        homeBinding = FragmentHomeBinding.inflate(inflater, container, false)
        popularMoviesRv = homeBinding.rvPopularMovies
        topRatedMoviesRv = homeBinding.rvTopRatedMovies
        upcomingMoviesRv = homeBinding.rvUpcomingMovies

        navController = findNavController()

        setUpPopularRv()
        setUpTopRatedRv()
        setUpUpcomingRv()

        viewModel.popularMovies.observe(viewLifecycleOwner, Observer { response ->
            when (response) {
                is Resource.Success -> {
                    hideProgressBar()
                    response.data?.let { popularResponse ->
                        popularAdapter.differ.submitList(popularResponse.results)
                    }
                }
                is Resource.Error -> {
                    hideProgressBar()
                    response.message?.let { message ->
                        Log.e(TAG, " An error occurred: $message")
                    }
                }
                is Resource.Loading -> {
                    showProgressBar()
                }
            }
        })

        viewModel.topRatedMovies.observe(viewLifecycleOwner, Observer { response ->
            when (response) {
                is Resource.Success -> {
                    hideProgressBar()
                    response.data?.let { popularResponse ->
                        topRatedAdapter.differ.submitList(popularResponse.results)
                    }
                }
                is Resource.Error -> {
                    hideProgressBar()
                    response.message?.let { message ->
                        Log.e(TAG, " An error occurred: $message")
                    }
                }
                is Resource.Loading -> {
                    showProgressBar()
                }
            }
        })

        viewModel.upcomingMovies.observe(viewLifecycleOwner, Observer { response ->
            when (response) {
                is Resource.Success -> {
                    hideProgressBar()
                    response.data?.let { popularResponse ->
                        upcomingAdapter.differ.submitList(popularResponse.results)
                    }
                }
                is Resource.Error -> {
                    hideProgressBar()
                    response.message?.let { message ->
                        Log.e(TAG, " An error occurred: $message")
                    }
                }
                is Resource.Loading -> {
                    showProgressBar()
                }
            }
        })

        viewModel.mainMoviePoster.observe(viewLifecycleOwner, Observer {
            Toast.makeText(requireActivity(),it.data,Toast.LENGTH_SHORT).show()
            Glide.with(requireActivity())
                .load("${it.data}")
                .centerCrop()
                .into(homeBinding.mainMoviePoster)
        })



        popularAdapter.setItemClickListener {
            findNavController().navigate(HomeFragmentDirections.actionHomeFragmentToMovieDetails(it))
        }

        topRatedAdapter.setItemClickListener {
            findNavController().navigate(HomeFragmentDirections.actionHomeFragmentToMovieDetails(it))
        }

        upcomingAdapter.setItemClickListener {
            findNavController().navigate(HomeFragmentDirections.actionHomeFragmentToMovieDetails(it))
        }

        return homeBinding.root
    }


    private fun hideProgressBar() {
        homeBinding.progressBar.visibility = View.INVISIBLE
    }

    private fun showProgressBar() {
        homeBinding.progressBar.visibility = View.VISIBLE
    }


    private fun setUpPopularRv() {
        popularAdapter = MovieAdapter()
        popularMoviesRv.apply {
            adapter = popularAdapter
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        }
    }

    private fun setUpTopRatedRv() {
        topRatedAdapter = MovieAdapter()
        topRatedMoviesRv.apply {
            adapter = topRatedAdapter
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        }
    }

    private fun setUpUpcomingRv() {
        upcomingAdapter = MovieAdapter()
        upcomingMoviesRv.apply {
            adapter = upcomingAdapter
            layoutManager =
                LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        }

    }
}