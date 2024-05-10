package com.github.ahmos0.apps.tegeigallery.android.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.github.ahmos0.apps.tegeigallery.infra.ApolloClientRepository
import com.github.ahmos0.apps.tegeigallery.infra.WorkModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class HomeScreenViewModel(
    private val repository: ApolloClientRepository = ApolloClientRepository()
) : ViewModel() {
    private val _workList = MutableStateFlow<List<WorkModel>>(emptyList())
    val workList: StateFlow<List<WorkModel>> = _workList

    init {
        fetchGalleryItems()
    }

    private fun fetchGalleryItems() {
        viewModelScope.launch {
            repository.fetchGalleryItems()
            repository.worksFlow.collectLatest { works ->
                //_urlList.value = works.map { it.url }
                _workList.value = works
            }
        }
    }
}