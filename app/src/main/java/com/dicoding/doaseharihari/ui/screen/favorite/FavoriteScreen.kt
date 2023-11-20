package com.dicoding.doaseharihari.ui.screen.favorite

import android.app.Application
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.dicoding.doaseharihari.R
import com.dicoding.doaseharihari.data.datamodel.DataDoa
import com.dicoding.doaseharihari.ui.component.DoaItem

@Composable
fun FavoriteScreen(
    navigateToDetail: (Long) -> Unit,
) {
    val context = LocalContext.current
    val _favoriteViewModel: FavoriteViewModel = viewModel(
        factory = FavoriteViewModelFactory(context.applicationContext as Application)
    )

    val list = _favoriteViewModel.readAllFavorite.observeAsState(listOf()).value

    Column {
        Text(
            text = "Favorite",
            fontWeight = FontWeight.Bold,
            fontSize = 24.sp,
            modifier = Modifier
                .padding(16.dp, 16.dp, 16.dp, 8.dp)
        )
        if (list.isEmpty()) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = context.getString(R.string.favorite)
                )
            }
        } else {
            LazyColumn {
                items(list) { doa ->
                    val data = DataDoa(
                        id = doa.id,
                        title = doa.title,
                        arab = doa.arab,
                        translation = doa.translation,
                        latin = doa.latin
                    )
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
}