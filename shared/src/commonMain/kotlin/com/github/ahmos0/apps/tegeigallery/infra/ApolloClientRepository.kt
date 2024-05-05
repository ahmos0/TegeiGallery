package com.github.ahmos0.apps.tegeigallery.infra

import AllItemsQuery
import com.apollographql.apollo.ApolloClient
import com.apollographql.apollo.api.ApolloExperimental
import com.apollographql.apollo.api.Response
import com.apollographql.apollo.network.http.ApolloHttpNetworkTransport
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf

@OptIn(ApolloExperimental::class, ExperimentalCoroutinesApi::class)
class ApolloClientRepository {
    var worksFlow: Flow<List<WorkModel>> = flowOf()
    private val apolloClient = ApolloClient(
        networkTransport = ApolloHttpNetworkTransport(
            serverUrl = "https://tegei-graphql-zm4cllso6q-an.a.run.app/graphql",
            headers = mapOf(
                "Accept" to "application/json",
                "Content-Type" to "application/json",
            )
        )
    )

    suspend fun fetchGalleryItems() {
        val responseData = apolloClient.query(AllItemsQuery()).execute()
        val workModels = mutableListOf<WorkModel>()
        worksFlow = flow {
            responseData.collect() { response ->
                response.data?.allItems?.map { item ->
                    item?.let {
                        workModels.add(WorkConverter.convertToWork(item))
                    }
                }
            }
            emit(workModels)
        }
    }
}

