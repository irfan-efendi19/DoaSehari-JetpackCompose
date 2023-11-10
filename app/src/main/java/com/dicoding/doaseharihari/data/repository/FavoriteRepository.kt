package com.dicoding.doaseharihari.data.repository

import androidx.lifecycle.LiveData
import com.dicoding.doaseharihari.data.dao.DoaDao
import com.dicoding.doaseharihari.data.entity.DoaEntity

class FavoriteRepository(
    private val doaDao: DoaDao
) {

    val readAllFavorite: LiveData<List<DoaEntity>> = doaDao.getFavoriteAll()

    fun isFavorite(id: Long) = doaDao.getFavoriteById(id)

    suspend fun addFavorite(doa: DoaEntity) {
        doaDao.insert(doa)
    }

    suspend fun deleteFavorite(doa: DoaEntity) {
        doaDao.delete(doa)
    }
}