package com.github.ahmos0.apps.tegeigallery.android.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.github.ahmos0.apps.tegeigallery.android.R
import com.github.ahmos0.apps.tegeigallery.infra.WorkModel


@Composable
fun WorkPageTemplate(
    uuid: String,
    getWorkByUuid: suspend (String) -> WorkModel?,
    ) {
    var work: WorkModel? by remember { mutableStateOf(null) }
    LaunchedEffect(uuid) {
        work = getWorkByUuid(uuid)
    }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        work?.let {
            UrlWorkImage(it)
        }
        Spacer(modifier = Modifier.height(20.dp))
        Text(
            text = work?.workName ?: "",
            fontSize = 20.sp,
            fontFamily = FontFamily(Font(R.font.noto_sans_jp_regular))
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = ("creator: " + work?.author),
            fontSize = 20.sp,
            fontFamily = FontFamily(Font(R.font.noto_sans_jp_regular))
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = ("description : " + work?.other),
            fontSize = 20.sp,
            fontFamily = FontFamily(Font(R.font.noto_sans_jp_regular))
        )

    }
}

@Composable
fun UrlWorkImage(item: WorkModel) {
    AsyncImage(
        model = item.url,
        contentDescription = null,
        modifier = Modifier
            .padding(8.dp)
    )
}