package com.github.ahmos0.apps.tegeigallery.android.ui

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun WorkPage(
    uuid: String,
    viewModel: WorkPageViewModel = viewModel(),
) {
    WorkPageTemplate(
        uuid = uuid,
        getWorkByUuid = { viewModel.getWorkByUuid(it) }
    )
}