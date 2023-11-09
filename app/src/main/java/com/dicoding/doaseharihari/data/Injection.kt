package com.dicoding.doaseharihari.data

import com.dicoding.doaseharihari.data.repository.RepositoryDoa

object Injection {
    fun provideRepository(): RepositoryDoa {
        return RepositoryDoa.getInstance()
    }
}