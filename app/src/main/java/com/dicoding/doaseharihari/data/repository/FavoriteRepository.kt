package com.dicoding.doaseharihari.data.repository

import androidx.lifecycle.LiveData
import com.dicoding.doaseharihari.data.dao.DoaDao
import com.dicoding.doaseharihari.data.entity.DoaEntity

class FavoriteRepository(
    private val doaDao: DoaDao
) {

    val readAllFavorite: LiveData<List<DoaEntity>> = doaDao.getFavoriteAll()

    fun isFavoriteAnimal(id: Long) = doaDao.getFavoriteById(id)

    suspend fun addFavorite(animal: DoaEntity) {
        doaDao.insert(animal)
    }

    suspend fun deleteFavorite(animal: DoaEntity) {
        doaDao.delete(animal)
    }
}