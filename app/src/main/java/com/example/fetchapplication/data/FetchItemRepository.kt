package com.example.fetchapplication.data

import com.example.fetchapplication.model.FetchItem
import com.example.fetchapplication.network.RetrofitInstance

interface FetchItemRepository {
    suspend fun getFetchItems(): List<FetchItem>
}

class NetworkFetchItemRepository: FetchItemRepository {
    override suspend fun getFetchItems(): List<FetchItem> {
        return RetrofitInstance.retrofitService.getItems()
    }
}