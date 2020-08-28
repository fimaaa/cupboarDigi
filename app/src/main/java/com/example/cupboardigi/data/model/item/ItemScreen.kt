package com.example.cupboardigi.data.model.item

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey

@Entity(tableName = "item_screen")
data class ItemScreen (
    @PrimaryKey
    @ColumnInfo(name = "id_screen")
    val idScreen: Long,
    @ColumnInfo(name = "id_board_in_screen")
    val idBoardInScreen: Long,
    @ColumnInfo(name = "name_screen")
    val nameScreen: String,
    @ColumnInfo(name = "is_shown")
    val isShown: Boolean
){
    @Ignore
    var itemStorages: List<ItemStorageUser>? = null
    constructor(
        idScreen: Long,
        idBoardInScreen: Long,
        nameScreen: String,
        isShown: Boolean,
        itemStorages: List<ItemStorageUser>) : this(
        idScreen,
        idBoardInScreen,
        nameScreen,
        isShown
    ) {
        this.itemStorages = itemStorages
    }
}