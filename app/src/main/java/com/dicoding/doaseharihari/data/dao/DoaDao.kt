package com.dicoding.doaseharihari.data.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.dicoding.doaseharihari.data.entity.DoaEntity

@Dao
interface DoaDao {
    @Query("SELECT * FROM favorites ORDER BY id_item DESC")
    fun getFavoriteAll(): LiveData<List<DoaEntity>>

    @Query("SELECT * FROM favorites WHERE id = :id")
    fun getFavoriteById(id: Long): LiveData<DoaEntity>

    @Insert
    suspend fun insert(doa: DoaEntity)

    @Update
    suspend fun update(doa: DoaEntity)

    @Delete
    suspend fun delete(doa: DoaEntity)
}