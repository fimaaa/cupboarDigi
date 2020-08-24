package com.example.cupboardigi.ui.menu

import android.app.Application
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.cupboardigi.base.BaseViewModel
import com.example.cupboardigi.data.endpoint.ApiServiceStorage
import com.example.cupboardigi.data.model.dao.PostDao
import com.example.cupboardigi.data.model.data.Resource
import com.example.cupboardigi.data.model.data.item.ItemCupBoard
import com.example.cupboardigi.data.model.data.item.ItemStorage
import com.example.cupboardigi.data.model.data.response.ResponseStorage
import com.example.cupboardigi.data.repository.ApiRepoStorage
import com.example.cupboardigi.util.ErrorUtils
import kotlinx.coroutines.launch
import javax.inject.Inject

class MenuViewModel(
        postDao: PostDao,
        application: Application
) : BaseViewModel<MenuNavigator>(application) {
        @Inject
        lateinit var apiServiceStorage: ApiServiceStorage
        private val apiStorage = ApiRepoStorage(apiServiceStorage, postDao)

        val itemStorageItem = postDao.findAllStorage()

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
}