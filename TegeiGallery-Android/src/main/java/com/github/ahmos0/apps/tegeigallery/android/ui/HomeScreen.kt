package com.github.ahmos0.apps.tegeigallery.android.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.github.ahmos0.apps.tegeigallery.android.R

@Composable
fun HomeScreen(viewModel: HomeScreenViewModel) {
    val workList = viewModel.workList.collectAsState()
    Column(

    ) {
        Text(
            text = "手芸ギャラリー",
            fontSize = 36.sp,
            fontFamily = FontFamily(Font(R.font.noto_sans_jp_extra_bold))
        )
        LazyVerticalGrid(
            columns = GridCells
                .Adaptive(196.dp),
            modifier = Modifier
                .padding(8.dp)
        ) {
            workList.value.forEach {
                item {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        UrlImage(it.url)
                        Text(
                            text = it.workName,
                            fontFamily = FontFamily(Font(R.font.noto_sans_jp_semi_bold)),
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun UrlImage(url: String) {
    AsyncImage(
        model = url,
        contentDescription = null,
        modifier = Modifier
            .padding(8.dp)
    )
}

@Preview
@Composable
fun PreviewHomeScreen() {
    HomeScreen(viewModel = HomeScreenViewModel())
}