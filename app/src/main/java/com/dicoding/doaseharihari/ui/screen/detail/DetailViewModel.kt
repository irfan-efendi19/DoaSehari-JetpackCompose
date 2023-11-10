package com.dicoding.doaseharihari.ui.screen.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dicoding.doaseharihari.data.Result
import com.dicoding.doaseharihari.data.datamodel.DataDoa
import com.dicoding.doaseharihari.data.repository.RepositoryDoa
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class DetailViewModel(
    private val repository: RepositoryDoa
) : ViewModel() {

    private val _result: MutableStateFlow<Result<DataDoa>> =
        MutableStateFlow(Result.Loading)
    val result: StateFlow<Result<DataDoa>>
        get() = _result

    fun getDoaById(animalId: Long) {
        viewModelScope.launch {
            _result.value = Result.Loading
            _result.value = Result.Success(repository.getDoaById(animalId))
        }
    }
}