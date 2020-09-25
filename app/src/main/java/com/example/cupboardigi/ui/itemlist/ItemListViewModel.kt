package com.example.cupboardigi.ui.itemlist

import android.app.Application
import androidx.lifecycle.viewModelScope
import com.example.cupboardigi.base.BaseViewModel
import com.example.cupboardigi.data.model.item.ItemSeries
import com.example.cupboardigi.database.dao.PostDao
import com.example.cupboardigi.util.Loading
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ItemListViewModel(
    private val storageDao: PostDao,
    application: Application
) : BaseViewModel<ItemListNavigator>(application) {
    val allSeries = storageDao.findAllItemSeries()
    val allTypeItem = storageDao.findAllItemType()

    fun addSeries(itemSeries: ItemSeries) {
        viewModelScope.launch {
            withContext(Dispatchers.Default) {
                storageDao.addItemSeries(itemSeries)
            }
            Loading.hideLoading()
        }
    }

}