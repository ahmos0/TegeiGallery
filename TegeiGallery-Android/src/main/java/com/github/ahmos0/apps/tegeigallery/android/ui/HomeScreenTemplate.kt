package com.github.ahmos0.apps.tegeigallery.android.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.github.ahmos0.apps.tegeigallery.android.R
import com.github.ahmos0.apps.tegeigallery.infra.WorkModel

@Composable
fun HomeScreenTemplate(
    workList: List<WorkModel>,
    fetchGalleryItems: () -> Unit,
    navController: NavController
) {
    Column {
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
            workList.forEach {
                item {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        UrlHomeImage(it) {
                            navController.navigate("work/${it.uuid}")
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun UrlHomeImage(item: WorkModel, onClick: () -> Unit) {
    AsyncImage(
        model = item.url,
        contentDescription = null,
        modifier = Modifier
            .padding(8.dp)
            .clickable(onClick = onClick)
    )
    Text(
        text = item.workName,
        fontFamily = FontFamily(Font(R.font.noto_sans_jp_semi_bold)),
    )
}
