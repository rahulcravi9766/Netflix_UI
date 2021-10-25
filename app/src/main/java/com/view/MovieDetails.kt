package com.view

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.moviehub.databinding.FragmentMovieDetailsBinding
import com.viewModel.MovieDetailsViewModel

class MovieDetails : Fragment() {


    private val args: MovieDetailsArgs by navArgs()
    private lateinit var movieBinding: FragmentMovieDetailsBinding
    private lateinit var viewModel: MovieDetailsViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        Log.i("on", "Movie detail fragment view")
        setHasOptionsMenu(true)
        movieBinding = FragmentMovieDetailsBinding.inflate(inflater, container, false)

        val viewModelProviderFactory = MovieDetailsViewModelProviderFactory(args.fullDetails)
        viewModel =
            ViewModelProvider(this, viewModelProviderFactory).get(MovieDetailsViewModel::class.java)


        viewModel.poster.observe(viewLifecycleOwner, Observer {
            Glide.with(requireActivity())
                .load("https://image.tmdb.org/t/p/w500${it}")
                .centerCrop()
                .into(movieBinding.moviePoster)
        })


        viewModel.backPoster.observe(viewLifecycleOwner, Observer {
            Glide.with(requireActivity())
                .load("https://image.tmdb.org/t/p/w1280${it}")
                .centerCrop()
                .into(movieBinding.movieBackdrop)
        })

        viewModel.name.observe(viewLifecycleOwner, Observer {
         movieBinding.movieTitle.text = it
        })

        viewModel.overview.observe(viewLifecycleOwner, Observer {
            movieBinding.movieOverview.text = it
        })

        viewModel.rating.observe(viewLifecycleOwner, Observer {
            movieBinding.ratingOfMovie.text = it
        })



        return movieBinding.root
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> findNavController().navigateUp()
        }
        return super.onOptionsItemSelected(item)
    }

}