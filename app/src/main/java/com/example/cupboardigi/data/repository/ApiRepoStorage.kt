package com.example.cupboardigi.data.repository

import com.example.cupboardigi.data.endpoint.ApiServiceStorage
import com.example.cupboardigi.database.dao.PostDao
import com.example.cupboardigi.data.model.response.ResponseStorage
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class ApiRepoStorage(
    private val apiServiceStorage: ApiServiceStorage,
    private val storageDao: PostDao
) {

    val storeageItem = storageDao.findAllStorage()
    val typeItem = storageDao.findAllItemType()
    suspend fun getStorage(): ResponseStorage.Response =
        withContext(Dispatchers.IO) {
            val response = apiServiceStorage.getStorageAsync().await()
            response
        }
}