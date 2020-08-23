package com.example.cupboardigi.data.repository

import com.example.cupboardigi.data.endpoint.ApiServiceStorage
import com.example.cupboardigi.data.model.Storage

class ApiRepoStorage(
    private val apiServiceStorage: ApiServiceStorage
) {
    suspend fun getStorage(): Storage.Response {
        return apiServiceStorage.getStorageAsync().await()
    }
}