package com.view

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.repository.HomeRepository
import com.viewModel.HomeViewModel

class HomeViewModelProviderFactory(
    private val homeRepository: HomeRepository
) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return HomeViewModel(homeRepository) as T
    }
}