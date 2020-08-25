package com.example.cupboardigi.ui.menu

import android.app.Application
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.cupboardigi.base.BaseViewModel
import com.example.cupboardigi.data.endpoint.ApiServiceStorage
import com.example.cupboardigi.data.model.dao.PostDao
import com.example.cupboardigi.data.model.data.Resource
import com.example.cupboardigi.data.model.data.table.ItemStorage
import com.example.cupboardigi.data.model.data.table.ItemType
import com.example.cupboardigi.data.model.data.response.ResponseStorage
import com.example.cupboardigi.data.repository.ApiRepoStorage
import com.example.cupboardigi.util.ErrorUtils
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

class MenuViewModel(
        private val storageDao: PostDao,
        application: Application
) : BaseViewModel<MenuNavigator>(application) {
        @Inject
        lateinit var apiServiceStorage: ApiServiceStorage
        private val apiStorage = ApiRepoStorage(apiServiceStorage, storageDao)

        val itemStorageItem = storageDao.findAllStorageJoin()
        val typeItem = storageDao.findAllItemType()
        val storeageItem = storageDao.findAllStorage()

        private val _responseStorage = MutableLiveData<Resource<ResponseStorage.Response>>()
        val responseStorage: MutableLiveData<Resource<ResponseStorage.Response>>
                get() = _responseStorage
        fun getStorage(){
                viewModelScope.launch {
                        _responseStorage.postValue(Resource.loading())
                        try {
                                val response = apiStorage.getStorage()
                                _responseStorage.postValue(Resource.success(response))
                        } catch (t:Throwable){
                                println("TAG message  = ${t.message}")
                                println("TAG cause  = ${t.cause}")
                                val response = Resource.error(
                                        ErrorUtils.getServiceErrorMsg(t),
                                        null,
                                        t
                                )
                                _responseStorage.postValue(response)
                        }
                }
        }

        suspend fun addTableStorage(){
                withContext(Dispatchers.IO){
                        val storages = listOf(
                                ItemStorage(
                                        0,
                                        1,
                                        "asdasd",
                                        "One Piece",
                                        22,
                                        1,
                                        0
                                ),
                                ItemStorage(
                                        1,
                                        1,
                                        "asdasd",
                                        "One Piece",
                                        23,
                                        1,
                                        0
                                ),
                                ItemStorage(
                                        2,
                                        1,
                                        "asdasd",
                                        "One Piece",
                                        24,
                                        1,
                                        0
                                ),
                                ItemStorage(
                                        3,
                                        3,
                                        "asdasd",
                                        "SSD",
                                        0,
                                        3,
                                        0
                                )
                        )
                        storageDao.addAllStorage(storages)
                        println("TAG storageSize = ${storages.size}")
                        println("TAG storageSize = ${storeageItem.value?.size}")
                }
        }

        suspend fun addTableTypeItem(){
                withContext(Dispatchers.IO) {
                        val tempTypeItem = listOf(
                                ItemType(
                                        1L,
                                        "Comic",
                                        1
                                ),
                                ItemType(
                                        2L,
                                        "Novel",
                                        1
                                ),
                                ItemType(
                                        3L,
                                        "Electronic",
                                        2
                                )
                        )
                        for(i in tempTypeItem.indices){
                                storageDao.addAllItemType(tempTypeItem[i])
                        }
                        println("TAG typeItem = ${tempTypeItem.size}")
                        println("TAG typeItem = ${typeItem.value?.size}")
                }
        }
}