package com.dicoding.doaseharihari.ui.screen.detail

import android.app.Application
import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.Divider
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.dicoding.doaseharihari.R
import com.dicoding.doaseharihari.data.Injection
import com.dicoding.doaseharihari.data.Result
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
    viewModel.result.collectAsState(initial = Result.Loading).value.let { _uiState ->
        when (_uiState) {
            is Result.Loading -> {
                viewModel.getDoaById(doaId)
            }

            is Result.Success -> {
                DetailContent(doa = _uiState.data)
            }

            is Result.Error -> {}
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

    Column(
        modifier = Modifier
            .padding(32.dp, 32.dp, 32.dp, 16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        Text(
            text = doa.title,
            fontWeight = FontWeight.Bold,
            fontSize = 26.sp,
            modifier = Modifier
                .padding(bottom = 16.dp)
        )
        Divider()
        Text(
            text = doa.arab,
            modifier = Modifier.padding(bottom = 16.dp),
            fontSize = 26.sp,
            textAlign = TextAlign.Center
        )
        Text(
            text = doa.latin, textAlign = TextAlign.Center, fontSize = 14.sp
        )
        Divider()
        Text(
            text = "Artinya:",
            fontSize = 14.sp,
            fontWeight = FontWeight.Bold
        )
        Text(
            text = doa.translation, textAlign = TextAlign.Center
        )
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
                        R.string.delete_favorite,
                        Toast.LENGTH_SHORT
                    ).show()
                } else {
                    mFavoriteViewModel.addFavorite(doaEntity)
                    Toast.makeText(context, R.string.add_favorite, Toast.LENGTH_SHORT)
                        .show()
                }
            },
            modifier = Modifier
                .padding(top = 52.dp)
        ) {
            if (isFavorite?.id == doa.id) {
                Icon(imageVector = Icons.Filled.Favorite, contentDescription = null)
            } else {
                Icon(imageVector = Icons.Filled.FavoriteBorder, contentDescription = null)
            }
        }
    }
}


