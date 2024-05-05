package com.github.ahmos0.apps.tegeigallery

import AllItemsQuery
import com.apollographql.apollo.ApolloClient
import com.apollographql.apollo.api.ApolloExperimental
import com.apollographql.apollo.api.Response
import com.apollographql.apollo.network.http.ApolloHttpNetworkTransport
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow

@OptIn(ApolloExperimental::class, ExperimentalCoroutinesApi::class)
class ApolloClientRepository {

    private val apolloClient = ApolloClient(
        networkTransport = ApolloHttpNetworkTransport(
            serverUrl = "https://tegei-graphql-zm4cllso6q-an.a.run.app/graphql",
            headers = mapOf(
                "Accept" to "application/json",
                "Content-Type" to "application/json",
            )
        )
    )

    suspend fun fetchGalleryItems(): Flow<Response<AllItemsQuery.Data>> {
        //        return apolloClient.query(AllItemsQuery()).execute()
        val flow = apolloClient.query(AllItemsQuery()).execute()
        flow.collect() { response ->
            response.data?.allItems?.get(1)?.url
        }
        return apolloClient.query(AllItemsQuery()).execute()
    }

    fun itemsConverter() {
        //TODO: convert response to Work
    }
}

data class Work(
    val uuid: String,
    val workName: String,
    val author: String,
    val url: String,
    val other: String,
)
