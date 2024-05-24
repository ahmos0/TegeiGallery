package com.github.ahmos0.apps.tegeigallery.infra

import AllItemsQuery

object WorkConverter {
    fun convertToWork(itemsQueryData: AllItemsQuery.AllItem): WorkModel {
        return WorkModel(
            uuid = itemsQueryData.uuid,
            workName = itemsQueryData.works,
            author = itemsQueryData.author,
            url = itemsQueryData.url,
            other = itemsQueryData.other,
        )
    }
}
