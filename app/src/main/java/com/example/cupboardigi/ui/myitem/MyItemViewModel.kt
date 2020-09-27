package com.example.cupboardigi.ui.myitem

import android.app.Application
import com.example.cupboardigi.base.BaseViewModel
import com.example.cupboardigi.database.dao.PostDao
import com.example.cupboardigi.ui.itemlist.ItemListNavigator

class MyItemViewModel(
    private val storageDao: PostDao,
    application: Application
) : BaseViewModel<MyItemNavigator>(application) {
    val allSeries = storageDao.findAllItemSeries()
    val allMyItem = storageDao.findAllStorage()
}