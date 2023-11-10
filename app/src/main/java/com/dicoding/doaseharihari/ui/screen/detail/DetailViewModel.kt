package com.dicoding.doaseharihari.ui.screen.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dicoding.doaseharihari.data.UiState
import com.dicoding.doaseharihari.data.datamodel.DataDoa
import com.dicoding.doaseharihari.data.repository.RepositoryDoa
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class DetailViewModel(
    private val repository: RepositoryDoa
) : ViewModel() {

    private val _uiState: MutableStateFlow<UiState<DataDoa>> =
        MutableStateFlow(UiState.Loading)
    val uiState: StateFlow<UiState<DataDoa>>
        get() = _uiState

    fun getDoaById(animalId: Long) {
        viewModelScope.launch {
            _uiState.value = UiState.Loading
            _uiState.value = UiState.Success(repository.getDoaById(animalId))
        }
    }
}