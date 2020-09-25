package com.example.cupboardigi.ui.splash

import android.app.Application
import androidx.lifecycle.viewModelScope
import com.example.cupboardigi.base.BaseViewModel
import com.example.cupboardigi.data.model.item.ItemType
import com.example.cupboardigi.database.dao.PostDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class SplashViewModel(
    private val storageDao: PostDao,
    application: Application
) : BaseViewModel<SplashNavigator>(application) {
    fun displaySplash() {
        viewModelScope.launch {
            delay(3000)
            navigator?.goToHome()
        }
    }
    val itemType = storageDao.findAllItemType()
    fun addItemType(){
        viewModelScope.launch {
            withContext(Dispatchers.Default) {
                storageDao.addItemType(
                    listOf(
                        ItemType(
                            0,
                            "Comic Book S",
                            ItemType.FormType.BOOK_SMALL.form
                        ),
                        ItemType(
                            0,
                            "Comic Book M",
                            ItemType.FormType.BOOK_MEDIUM.form
                        ),
                        ItemType(
                            0,
                            "Comic Book L",
                            ItemType.FormType.BOOK_LARGE.form
                        ),
                        ItemType(
                            0,
                            "Comic Digital",
                            ItemType.FormType.BOOK_MEDIUM.form
                        ),
                        ItemType(
                            0,
                            "Other",
                            ItemType.FormType.UNKNOWN.form
                        )
                    )
                )
            }
            displaySplash()
        }
    }
}