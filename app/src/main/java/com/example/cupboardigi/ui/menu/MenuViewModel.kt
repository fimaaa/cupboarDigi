package com.example.cupboardigi.ui.menu

import android.app.Application
import androidx.lifecycle.viewModelScope
import com.example.cupboardigi.base.BaseViewModel
import com.example.cupboardigi.data.endpoint.ApiServiceStorage
import com.example.cupboardigi.data.repository.ApiRepoStorage
import kotlinx.coroutines.launch
import javax.inject.Inject

class MenuViewModel(application: Application) : BaseViewModel<MenuNavigator>(application) {
        @Inject
        lateinit var apiServiceStorage: ApiServiceStorage
        val apiStorage = ApiRepoStorage(apiServiceStorage)

        fun getStorage(){
                viewModelScope.launch {
                        try {
                                val response = apiStorage.getStorage()
                        } catch (t:Throwable){
                                println("TAG message  = ${t.message}")
                                println("TAG cause  = ${t.cause}")
                        }
                }
        }
}