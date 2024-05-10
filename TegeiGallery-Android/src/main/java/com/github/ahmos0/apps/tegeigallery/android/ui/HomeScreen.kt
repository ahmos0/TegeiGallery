package com.github.ahmos0.apps.tegeigallery.android.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController

@Composable
fun HomeScreen(
    viewModel: HomeScreenViewModel = viewModel(),
    navController: NavController
) {
    val workList = viewModel.workList.collectAsState()
    HomeScreenTemplate(
        workList = workList.value,
        fetchGalleryItems = { viewModel.fetchGalleryItems() },
        navController = navController
    )
}