package com.dicoding.doaseharihari.ui.screen.detail

import android.app.Application
import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.Divider
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.dicoding.doaseharihari.data.Injection
import com.dicoding.doaseharihari.data.UiState
import com.dicoding.doaseharihari.data.ViewModelFactory
import com.dicoding.doaseharihari.data.datamodel.DataDoa
import com.dicoding.doaseharihari.data.entity.DoaEntity
import com.dicoding.doaseharihari.ui.screen.favorite.FavoriteViewModel
import com.dicoding.doaseharihari.ui.screen.favorite.FavoriteViewModelFactory

@Composable
fun DetailScreen(
    doaId: Long,
    viewModel: DetailViewModel = viewModel(
        factory = ViewModelFactory(
            Injection.provideRepository()
        )
    )
) {
    viewModel.uiState.collectAsState(initial = UiState.Loading).value.let { _uiState ->
        when (_uiState) {
            is UiState.Loading -> {
                viewModel.getAnimalById(doaId)
            }

            is UiState.Success -> {
                DetailContent(doa = _uiState.data)
            }

            is UiState.Error -> {}
        }
    }
}

@Composable
fun DetailContent(doa: DataDoa) {
    val context = LocalContext.current
    val mFavoriteViewModel: FavoriteViewModel = viewModel(
        factory = FavoriteViewModelFactory(context.applicationContext as Application)
    )

    val isFavorite = mFavoriteViewModel.isFavorite(doa.id).observeAsState().value

    Column {
        FloatingActionButton(
            onClick = {
                val doaEntity = DoaEntity(
                    id = doa.id,
                    title = doa.title,
                    arab = doa.arab,
                    latin = doa.latin,
                    translation = doa.translation
                )
                if (isFavorite?.id == doa.id) {
                    mFavoriteViewModel.deleteFavorite(isFavorite)
                    Toast.makeText(
                        context,
                        "Favorite deleted successfully.",
                        Toast.LENGTH_SHORT
                    ).show()
                } else {
                    mFavoriteViewModel.addFavorite(doaEntity)
                    Toast.makeText(context, "Favorite added successfully.", Toast.LENGTH_SHORT)
                        .show()
                }
            },
            modifier = Modifier
                .padding(bottom = 16.dp)
        ) {
            if (isFavorite?.id == doa.id) {
                Icon(imageVector = Icons.Filled.Favorite, contentDescription = null)
            } else {
                Icon(imageVector = Icons.Filled.FavoriteBorder, contentDescription = null)
            }
        }
        Text(
            text = doa.title,
            fontWeight = FontWeight.Bold,
            fontSize = 26.sp,
            modifier = Modifier
                .padding(bottom = 16.dp)
        )
        Divider()
        Text(
            text = "Description:",
            fontSize = 14.sp,
            fontWeight = FontWeight.Bold
        )
        Text(
            text = doa.arab,
            modifier = Modifier
                .padding(bottom = 16.dp)
        )
        Divider()
        Text(
            text = "Populasi:",
            fontSize = 14.sp,
            fontWeight = FontWeight.Bold
        )
        Text(
            text = doa.translation
        )
    }
}



