package com.example.cupboardigi.data.model.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.cupboardigi.data.model.data.response.ResponseStorage
import com.example.cupboardigi.data.model.data.table.ItemTypeWithItem
import com.example.cupboardigi.data.model.data.table.ItemStorage
import com.example.cupboardigi.data.model.data.table.ItemType

@Dao
interface PostDao {
//    @get:Query("SELECT * FROM storage")
//    val all: MutableList<Storage>

//    @Insert
//    fun insertAll(vararg posts: Storage)

    @Transaction
    @Query("SELECT * FROM item_storage")
    fun findAllStorage(): LiveData<List<ItemStorage>>

    @Transaction
    @Query("SELECT * FROM item_type")
    fun findAllItemType(): LiveData<List<ItemType>>

    @Transaction
    @Query("SELECT * FROM item_type")
    fun findAllCupboard(): LiveData<List<ItemTypeWithItem>>

    @Transaction
    @Query(
        "SELECT * FROM item_storage INNER JOIN item_type ON item_storage.type_item=item_type.id"
    )
    fun findAllStorageJoin(): LiveData<List<ResponseStorage.Response.Data>>

    @Transaction
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addAllStorage(ItemStorage: List<ItemStorage>?)

    @Transaction
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addAllItemType(itemType: ItemType): Long

}