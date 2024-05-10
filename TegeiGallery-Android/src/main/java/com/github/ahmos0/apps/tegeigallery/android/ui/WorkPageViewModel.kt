package com.github.ahmos0.apps.tegeigallery.android.ui

import androidx.lifecycle.ViewModel
import com.github.ahmos0.apps.tegeigallery.infra.ApolloClientRepository
import com.github.ahmos0.apps.tegeigallery.infra.WorkModel

class WorkPageViewModel(
    private val repository: ApolloClientRepository = ApolloClientRepository()
): ViewModel() {
    suspend fun getWorkByUuid(uuid: String): WorkModel? {
        return repository.getWorkByUuid(uuid)
    }
}