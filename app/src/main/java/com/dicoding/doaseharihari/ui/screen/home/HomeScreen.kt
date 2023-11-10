package com.dicoding.doaseharihari.ui.screen.home

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.dicoding.doaseharihari.data.Injection
import com.dicoding.doaseharihari.data.Result
import com.dicoding.doaseharihari.data.ViewModelFactory
import com.dicoding.doaseharihari.data.datamodel.DataDoa
import com.dicoding.doaseharihari.ui.component.DoaItem
import com.dicoding.doaseharihari.ui.component.Search

@Composable
fun HomeScreen(
    viewModel: HomeViewModel = viewModel(
        factory = ViewModelFactory(Injection.provideRepository())
    ),
    navigateToDetail: (Long) -> Unit,
) {
    viewModel.result.collectAsState(initial = Result.Loading).value.let { _uiState ->
        when (_uiState) {
            is Result.Loading -> {
                viewModel.getAllDoa()
            }

            is Result.Success -> {
                HomeScreen(
                    _uiState.data,
                    navigateToDetail = navigateToDetail,
                    viewModel
                )
            }

            is Result.Error -> {}
        }
    }
}


@Composable
fun HomeScreen(
    doaList: List<DataDoa>,
    navigateToDetail: (Long) -> Unit,
    viewModel: HomeViewModel
) {
    val query by viewModel.query

    Column {
        Search(query = query, onQueryChange = viewModel::search)
        LazyColumn(
            modifier = Modifier
                .testTag("ListDoa")
                .fillMaxSize()
                .padding(start = 5.dp, end = 5.dp)
        ) {
            items(doaList) { data ->
                DoaItem(
                    doa = data,
                    modifier = Modifier.clickable {
                        navigateToDetail(data.id)
                    }
                )
            }
        }
    }
}