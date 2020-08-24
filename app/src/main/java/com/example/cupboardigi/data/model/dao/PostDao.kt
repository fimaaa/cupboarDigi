package com.example.cupboardigi.data.model.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.cupboardigi.data.model.data.item.ItemCupBoard
import com.example.cupboardigi.data.model.data.item.ItemStorage

@Dao
interface PostDao {
//    @get:Query("SELECT * FROM storage")
//    val all: MutableList<Storage>

//    @Insert
//    fun insertAll(vararg posts: Storage)

    @Query("SELECT * FROM item_storage")
    fun findAllStorage(): LiveData<List<ItemStorage>>

    @Query("SELECT * FROM ItemType")
    fun findAllCupboard(): LiveData<List<ItemCupBoard>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addAllStorage(ItemStorage: List<ItemStorage>?)
}