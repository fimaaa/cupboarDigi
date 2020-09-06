package com.example.cupboardigi.database.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.cupboardigi.data.model.item.*
import com.example.cupboardigi.data.model.table.RelationItemInScreen
import com.example.cupboardigi.data.model.table.RelationScreenInBoard
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import okhttp3.internal.wait

@Dao
interface PostDao {
    // ItemType
    @Transaction
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addItemType(itemType: ItemType)

    @Transaction
    @Query("SELECT * FROM item_type")
    fun findAllItemType(): LiveData<List<ItemType>>

    // ItemSeries
    @Transaction
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addItemSeries(itemSeries: List<ItemSeries>)

    @Transaction
    @Query("SELECT * FROM item_series")
    fun findAllItemSeries(): LiveData<List<ItemSeries>>

    // ItemStorage
    @Transaction
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addAllStorage(ItemStorage: List<ItemStorage>?)

    @Transaction
    @Query("SELECT * FROM item_storage")
    fun findAllStorage(): LiveData<List<ItemStorage>>

    // ItemStorageUser
    @Transaction
    fun addStorageUser(itemStorage: ItemStorageUser){
        if (findStorageUser(itemStorage.idItemUser).value?.isNotEmpty() == true) {
            updateStorageUser(itemStorage)
        } else {
            val list = listOf(
                itemStorage
            )
            addAllStorageUser(list)
        }
    }

    @Transaction
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addAllStorageUser(itemStorages: List<ItemStorageUser>?)

    @Transaction
    @Query("SELECT * FROM item_storage_user")
    fun findAllStorageUser(): LiveData<List<ItemStorageUser>>

    @Transaction
    @Query("SELECT * FROM item_storage_user WHERE id_item_user=(:idStorageUser)")
    fun findStorageUser(idStorageUser: Long): LiveData<List<ItemStorageUser>>

    @Update
    fun updateStorageUser(itemStorages: ItemStorageUser)


    // ItemBoard
    @Transaction
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addItemBoard(itemBoard: ItemBoard)

    @Transaction
    @Query("SELECT * FROM item_board")
    fun findAllBoard(): LiveData<List<ItemBoard>>

    // ItemScreen
    @Transaction
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addItemScreen(itemScreen: ItemScreen)

    @Transaction
    @Query("SELECT * FROM item_board")
    fun findAllScreen(): LiveData<List<RelationScreenInBoard>>

    @Transaction
    @Query("SELECT * FROM item_screen")
    fun findAllItemInScreen(): LiveData<List<RelationItemInScreen>>

    @Transaction
    @Query("SELECT * FROM item_screen WHERE id_screen=(:idScreen)")
    fun findItemInScreen(idScreen:Long): LiveData<RelationItemInScreen>
}