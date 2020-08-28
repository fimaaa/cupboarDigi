package com.example.cupboardigi.database.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.cupboardigi.data.model.item.ItemBoard
import com.example.cupboardigi.data.model.item.ItemScreen
import com.example.cupboardigi.data.model.item.ItemStorage
import com.example.cupboardigi.data.model.item.ItemType
import com.example.cupboardigi.data.model.table.CrossRefItemScreen
import com.example.cupboardigi.data.model.table.RelationScreenInBoard

@Dao
interface PostDao {
    // ItemType
    @Transaction
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addItemType(itemType: ItemType)

    @Transaction
    @Query("SELECT * FROM item_type")
    fun findAllItemType(): LiveData<List<ItemType>>

    // ItemStorage
    @Transaction
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addAllStorage(ItemStorage: List<ItemStorage>?)

    @Transaction
    @Query("SELECT * FROM item_storage")
    fun findAllStorage(): LiveData<List<ItemStorage>>


    // ItemBoard
    @Transaction
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addItemBoard(itemBoard: ItemBoard)

    @Transaction
    @Query("SELECT * FROM item_board")
    fun findAllBoard(): LiveData<List<RelationScreenInBoard>>

    // ItemScreen
    @Transaction
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addItemScreen(itemScreen: ItemScreen)

    @Transaction
    @Query("SELECT * FROM item_board")
    fun findAllScreen(): LiveData<List<RelationScreenInBoard>>


    // CrossItemScreen
    @Transaction
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addCrossRefItemScreen(crossRefItemScreen: CrossRefItemScreen)

    @Transaction
    @Query("SELECT * FROM crossrefitemscreen WHERE id_item_user=(:idItemUser) AND id_screen=(:idScreen)")
    fun findCrossItemScreen(idItemUser: Long, idScreen: Long): List<CrossRefItemScreen>?

    @Transaction
    fun addQty(crossRefItemScreen: CrossRefItemScreen){
        if(findCrossItemScreen(crossRefItemScreen.itemID, crossRefItemScreen.screenID)?.size?:0 <= 0){
            addCrossRefItemScreen(crossRefItemScreen)
        }else{
            updateCrossRefItemScreen(crossRefItemScreen)
        }
    }
    @Update
    fun updateCrossRefItemScreen(crossRefItemScreen: CrossRefItemScreen)

}