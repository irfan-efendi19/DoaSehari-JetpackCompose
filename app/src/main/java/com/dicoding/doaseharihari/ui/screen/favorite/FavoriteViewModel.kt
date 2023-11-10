package com.dicoding.doaseharihari.ui.screen.favorite

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.dicoding.doaseharihari.data.datamodel.AppDatabase
import com.dicoding.doaseharihari.data.repository.FavoriteRepository
import com.dicoding.doaseharihari.data.entity.DoaEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class FavoriteViewModel(application: Application) : AndroidViewModel(application) {

    val readAllFavorite: LiveData<List<DoaEntity>>
    private val favoriteRepository: FavoriteRepository

    init {
        val doaDao = AppDatabase.getDatabase(application).doaDao()
        favoriteRepository = FavoriteRepository(doaDao)
        readAllFavorite = favoriteRepository.readAllFavorite
    }

    fun addFavorite(doa: DoaEntity) {
        viewModelScope.launch(Dispatchers.IO) {
            favoriteRepository.addFavorite(doa)
        }
    }

    fun deleteFavorite(doa: DoaEntity) {
        viewModelScope.launch(Dispatchers.IO) {
            favoriteRepository.deleteFavorite(doa)
        }
    }

    fun isFavorite(id: Long) = favoriteRepository.isFavorite(id)
}

class FavoriteViewModelFactory(
    private val application: Application
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        @Suppress("UNCHECKED_CAST")
        if (modelClass.isAssignableFrom(FavoriteViewModel::class.java)) {
            return FavoriteViewModel(application) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
