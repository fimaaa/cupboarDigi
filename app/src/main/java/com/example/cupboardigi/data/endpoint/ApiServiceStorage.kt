package com.example.cupboardigi.data.endpoint

import com.example.cupboardigi.data.model.data.response.ResponseStorage
import kotlinx.coroutines.Deferred
import retrofit2.http.GET

interface ApiServiceStorage {
    @GET("status")
    fun getStorageAsync(): Deferred<ResponseStorage.Response>
}