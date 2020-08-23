package com.example.cupboardigi.data.endpoint

import com.example.cupboardigi.data.model.Storage
import kotlinx.coroutines.Deferred
import retrofit2.http.GET


interface ApiServiceStorage {
    @GET("status")
    fun getStorageAsync(): Deferred<Storage.Response>
}