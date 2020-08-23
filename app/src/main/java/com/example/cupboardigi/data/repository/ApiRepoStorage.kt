package com.example.cupboardigi.data.repository

import com.example.cupboardigi.data.endpoint.ApiServiceStorage
import com.example.cupboardigi.data.model.Storage
import javax.inject.Inject

class ApiRepoStorage{

    @Inject
    lateinit var apiServiceStorage: ApiServiceStorage

    suspend fun getStorage(): Storage.Response {
//        return apiServiceStorage.getStorageAsync().await()
        return Storage.Response(
            200,
            null,
            true,
            "From get Storage"
        )
    }
}