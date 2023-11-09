package com.dicoding.doaseharihari.data.repository

import com.dicoding.doaseharihari.data.datamodel.DataDoa
import com.dicoding.doaseharihari.data.datamodel.DummyDataDoa
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

class RepositoryDoa {

    private val listDoa = mutableListOf<DataDoa>()

    init {
        DummyDataDoa.dummyDoa.forEach {
            listDoa.add(
                DataDoa(
                    it.id,
                    it.title,
                    it.arab,
                    it.latin,
                    it.translation
                )
            )
        }
    }

    fun getAllDoa(): Flow<List<DataDoa>> {
        return flowOf(listDoa)
    }

    fun getDoaById(doaId: Long): DataDoa {
        return listDoa.first {
            it.id == doaId
        }
    }

    fun searchDoa(query: String): Flow<List<DataDoa>> {
        return flowOf(
            listDoa
                .filter {
                    it.title.contains(query, ignoreCase = true)
                }
        )
    }

    companion object {
        @Volatile
        private var instance: RepositoryDoa? = null

        fun getInstance(): RepositoryDoa =
            instance ?: synchronized(this) {
                RepositoryDoa().apply {
                    instance = this
                }
            }
    }
}