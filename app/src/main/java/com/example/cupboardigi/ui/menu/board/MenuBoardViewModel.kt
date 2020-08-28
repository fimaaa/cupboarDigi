package com.example.cupboardigi.ui.menu.board

import android.app.Application
import androidx.lifecycle.viewModelScope
import com.example.cupboardigi.base.BaseViewModel
import com.example.cupboardigi.database.dao.PostDao
import com.example.cupboardigi.data.model.item.ItemStorage
import com.example.cupboardigi.data.model.item.ItemType
import com.example.cupboardigi.ui.menu.ContainerMenuNavigator
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MenuBoardViewModel(
    private val storageDao: PostDao,
    application: Application
) : BaseViewModel<ContainerMenuNavigator>(application) {
    val itemStorageItem = storageDao.findAllStorage()
    val itemType = listOf(
        ItemType(
            1,
            "Comic",
            1
        ),
        ItemType(
            2,
            "Novel",
            1
        ),
        ItemType(
            3,
            "Electronic",
            2
        ),
        ItemType(
            4,
            "Action Figure",
            3
        )
    )
    init {
        viewModelScope.launch {
            addType()
            addStorage()
        }
    }
    suspend fun addStorage(){
        withContext(Dispatchers.IO) {
            storageDao.addAllStorage(
                listOf(
                    ItemStorage(
                        1,
                        itemType[0],
                        "",
                        "OnePiece",
                        22
                    ),
                    ItemStorage(
                        2,
                        itemType[0],
                        "",
                        "OnePiece",
                        23
                    ),
                    ItemStorage(
                        3,
                        itemType[2],
                        "",
                        "SSD",
                        22
                    )
                )
            )
        }
    }

    suspend fun addType(){
        withContext(Dispatchers.IO){
            for (i in itemType.indices) {
                storageDao.addItemType(itemType[i])

            }
        }
    }
}