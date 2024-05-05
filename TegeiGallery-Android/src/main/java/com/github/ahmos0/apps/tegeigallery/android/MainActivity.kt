package com.github.ahmos0.apps.tegeigallery.android

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.compose.rememberAsyncImagePainter
import coil.compose.rememberImagePainter
import com.github.ahmos0.apps.tegeigallery.infra.ApolloClientRepository
import com.github.ahmos0.apps.tegeigallery.infra.WorkModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.emptyFlow
import kotlinx.coroutines.launch
import java.util.concurrent.Flow

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApplicationTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    ScreenImage()
                }
            }
        }
    }
}

@Composable
fun ScreenImage() {
    val repository = ApolloClientRepository()
    val hoge = rememberCoroutineScope()
    var urlList by remember { mutableStateOf(emptyList<String>()) }


    LaunchedEffect(Unit) {
        hoge.launch {
            repository.fetchGalleryItems()
            repository.worksFlow.collectLatest { works ->
                works.forEach {
                    Log.d("hogehoge", "work: ${it.url}")
                    urlList = works.map { it.url }
                }
            }
        }
    }
    urlList.forEach {
        UrlImage(it)
    }
}

@Composable
fun UrlImage(url: String) {
    AsyncImage(model = url, contentDescription = null)
}

