package com.dicoding.doaseharihari.ui.screen.home

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dicoding.doaseharihari.data.UiState
import com.dicoding.doaseharihari.data.datamodel.DataDoa
import com.dicoding.doaseharihari.data.repository.RepositoryDoa
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch

class HomeViewModel(
    private val repository: RepositoryDoa
) : ViewModel() {

    private val _uiState: MutableStateFlow<UiState<List<DataDoa>>> =
        MutableStateFlow(UiState.Loading)
    val uiState: StateFlow<UiState<List<DataDoa>>>
        get() = _uiState

    fun getAllDoa() {
        viewModelScope.launch {
            repository.getAllDoa()
                .catch {
                    _uiState.value = UiState.Error(it.message.toString())
                }
                .collect { doa ->
                    _uiState.value = UiState.Success(doa)
                }
        }
    }

    private val _query = mutableStateOf("")
    val query: State<String> get() = _query

    fun search(newQuery: String) {
        _query.value = newQuery

        viewModelScope.launch {
            repository.searchDoa(newQuery)
                .catch {
                    _uiState.value = UiState.Error(it.message.toString())
                }
                .collect { data ->
                    _uiState.value = UiState.Success(data)
                }
        }
    }
}