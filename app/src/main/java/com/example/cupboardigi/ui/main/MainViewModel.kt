package com.example.cupboardigi.ui.main

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cupboardigi.base.BaseViewModel
import com.example.cupboardigi.data.endpoint.ApiServiceStorage
import com.example.cupboardigi.data.repository.ApiRepoStorage
import com.example.cupboardigi.util.Toast_Default
import com.example.cupboardigi.util.Toast_Error
import com.example.cupboardigi.util.showToast
import kotlinx.coroutines.launch
import javax.inject.Inject

class MainViewModel(application: Application) : BaseViewModel<MainNavigator>(application) {
        @Inject
        lateinit var apiServiceStorage: ApiServiceStorage
        val apiStorage = ApiRepoStorage(apiServiceStorage)

        private val mContext = getApplication<Application>().applicationContext

        fun getStorage(){
                viewModelScope.launch {
                        try {
                                val response = apiStorage.getStorage()
                                mContext.showToast(response.message, Toast_Default)
                        } catch (t:Throwable){
                                mContext.showToast(t.message.toString(), Toast_Error)
                                println("TAG message  = ${t.message}")
                                println("TAG cause  = ${t.cause}")
                        }
                }

        }
}