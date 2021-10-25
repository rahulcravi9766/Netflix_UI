package com.view

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.model.Result
import com.viewModel.MovieDetailsViewModel

class MovieDetailsViewModelProviderFactory(val  fullDetails: Result) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return MovieDetailsViewModel(fullDetails) as T
    }
}