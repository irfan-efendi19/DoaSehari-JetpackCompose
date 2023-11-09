package com.dicoding.doaseharihari.data

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.dicoding.doaseharihari.data.repository.RepositoryDoa
import com.dicoding.doaseharihari.ui.screen.detail.DetailViewModel
import com.dicoding.doaseharihari.ui.screen.home.HomeViewModel

class ViewModelFactory(private val repository: RepositoryDoa) :
    ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(HomeViewModel::class.java)) {
            return HomeViewModel(repository) as T
        } else if (modelClass.isAssignableFrom(DetailViewModel::class.java)) {
            return DetailViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class: " + modelClass.name)
    }
}