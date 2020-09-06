package com.example.cupboardigi.ui.menu.board

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.cupboardigi.base.BaseViewModel
import com.example.cupboardigi.data.model.item.*
import com.example.cupboardigi.data.model.table.RelationItemInScreen
import com.example.cupboardigi.database.dao.PostDao
import com.example.cupboardigi.ui.adapter.AdapterScreen
import com.example.cupboardigi.ui.menu.ContainerMenuNavigator
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MenuBoardViewModel(
    private val storageDao: PostDao,
    application: Application
) : BaseViewModel<ContainerMenuNavigator>(application) {
    val itemCupBoard = storageDao.findAllScreen()
    fun getItem(listScreen: List<ItemScreen>){
        adapter.clearData()
        for(i in listScreen.indices){
            getItemScreen(
                listScreen[i].idScreen
            ).observeForever{
                val screen = it.screen
                screen.itemStorages = it.storageUser
                adapter.addData(screen)

            }
        }
    }

    var adapter: AdapterScreen = AdapterScreen(
        {

        },
        {

        },
        {

        })

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

    val itemSeries = listOf(
        ItemSeries(
            0,
            "One Piece",
            itemType[0],
            93,
            false,
            ""
        ),
        ItemSeries(
            1,
            "SSD",
            itemType[2],
            0,
            true,
            ""
        )
    )

    init {
        viewModelScope.launch {
//            addType()
//            addSeries()
//            addStorage()
//            addBoard()
//            addScreen()
//            addStorageUser()
        }
    }

    suspend fun addSeries(){
        withContext(Dispatchers.IO){
            storageDao.addItemSeries(itemSeries)
        }
    }

    suspend fun addStorage(){
        withContext(Dispatchers.IO) {
            storageDao.addAllStorage(
                listOf(
                    ItemStorage(
                        1,
                        itemSeries[0],
                        22
                    ),
                    ItemStorage(
                        2,
                        itemSeries[0],
                        23
                    ),
                    ItemStorage(
                        3,
                        itemSeries[1],
                        0
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

    suspend fun addBoard(){
        withContext(Dispatchers.IO){
                storageDao.addItemBoard(
                    ItemBoard(
                        1,
                        ""
                    )
                )
        }
    }

    suspend fun addScreen(){
        withContext(Dispatchers.IO){
            for(i in 1 .. 4){
                storageDao.addItemScreen(
                    ItemScreen(
                        i.toLong(),
                        1,
                        "asdasd-$i",
                        false
                    )
                )
            }
        }
    }

    suspend fun addStorageUser(){
        withContext(Dispatchers.IO){
            storageDao.addAllStorageUser(
                listOf(
                    ItemStorageUser(
                        0,
                        ItemStorage(
                            1,
                            itemSeries[0],
                            22
                        ),
                        1
                    ),
                    ItemStorageUser(
                        1,
                        ItemStorage(
                            1,
                            itemSeries[0],
                            22
                        ),
                        1
                    ),
                    ItemStorageUser(
                        2,
                        ItemStorage(
                            2,
                            itemSeries[0],
                            23
                        ),
                        1
                    ),
                    ItemStorageUser(
                        3,
                        ItemStorage(
                            3,
                            itemSeries[1],
                            0
                        ),
                        1
                    )
                )
            )
        }
    }

    private fun getItemScreen(idScreen: Long): LiveData<RelationItemInScreen>{
        return storageDao.findItemInScreen(idScreen)
    }

}