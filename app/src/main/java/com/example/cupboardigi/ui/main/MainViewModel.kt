package com.example.cupboardigi.ui.main

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cupboardigi.base.BaseViewModel
import com.example.cupboardigi.data.repository.ApiRepoStorage
import com.example.cupboardigi.util.Toast_Default
import com.example.cupboardigi.util.showToast
import kotlinx.coroutines.launch
import javax.inject.Inject

class MainViewModel(application: Application) : BaseViewModel<MainNavigator>(application) {
        val apiStorage = ApiRepoStorage()
        private val mContext = getApplication<Application>().applicationContext

        fun getStorage(){
                viewModelScope.launch {
                        val response = apiStorage.getStorage()
                        mContext.showToast(response.message, Toast_Default)
                }

        }
}